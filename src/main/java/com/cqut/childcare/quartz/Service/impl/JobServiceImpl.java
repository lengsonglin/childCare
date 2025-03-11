package com.cqut.childcare.quartz.Service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.common.exception.QrtzJobException;
import com.cqut.childcare.quartz.Service.JobService;
import com.cqut.childcare.quartz.dao.QrtzJobDao;
import com.cqut.childcare.quartz.domain.QrtzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description
 * @Author Faiz
 * @ClassName JobServiceImpl
 * @Version 1.0
 */
@Service
public class JobServiceImpl implements JobService  {
    private static final String JOB_GROUP_NAME = "JOB_GROUP_NAME";
    private static final String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private QrtzJobDao qrtzJobDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addJob(QrtzJob qrtzJob) throws SchedulerException {
        Class<? extends Job> jobClass;
        try {
            jobClass = (Class<? extends Job>) Class.forName(qrtzJob.getClassName());
        } catch (ClassNotFoundException e) {
            throw new QrtzJobException("任务实现类不存在!");
        }

        QrtzJob job = qrtzJobDao.getByJobName(qrtzJob.getJobName());
        if (!Objects.isNull(job)) {
            throw new QrtzJobException("当前任务名称已存在，添加任务失败，请核实");
        }
        if (!CronExpression.isValidExpression(qrtzJob.getCronExp())) {
            throw new QrtzJobException("cron表达式错误，请检测！");
        }

        //构建 Job Detail
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(new JobKey(qrtzJob.getJobName(), JOB_GROUP_NAME)).storeDurably().build(); //创建完jobDetail之后，使用语句传参数值，方便定时任务内部识别它是什么标识
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Map<String, Object> jobParams = qrtzJob.getJobParams();
        jobDataMap.putAll(jobParams);

        //构建Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(qrtzJob.getCronExp()))
                .withIdentity(qrtzJob.getJobName(),TRIGGER_GROUP_NAME).build();
        //调度任务
        scheduler.scheduleJob(jobDetail, trigger);

        if ("PAUSED".equals(qrtzJob.getStatus())) {
            scheduler.pauseJob(JobKey.jobKey(qrtzJob.getJobName(),JOB_GROUP_NAME));
        }
        qrtzJob.setExeCount(0L);
        qrtzJobDao.save(qrtzJob);
    }

    @Override
    public QrtzJob getById(Long id) {
        return qrtzJobDao.getById(id);
    }

    @Override
    public void pauseJob(String jobName) {
        //先停止任务，在更改数据库状态
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName,JOB_GROUP_NAME));
        } catch (SchedulerException e) {
            throw new QrtzJobException("暂停任务失败");
        }
        qrtzJobDao.upStatusByJobName(jobName);
    }

    @Override
    public void deleteJob(String jobName) {
        try {
            scheduler.deleteJob(JobKey.jobKey(jobName,JOB_GROUP_NAME));
        } catch (SchedulerException e) {
            throw new QrtzJobException("删除任务失败");
        }
        qrtzJobDao.deleteJobByJobName(jobName);
    }

    @Override
    public void resumeJob(String jobName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName,JOB_GROUP_NAME));
        } catch (SchedulerException e) {
            throw new QrtzJobException("重启任务失败");
        }
        qrtzJobDao.resumeJobByJobName(jobName);
    }

    @Override
    public PageBaseResp<QrtzJob> getJobs(PageBaseReq request) {
        Page<QrtzJob> jobsPage = qrtzJobDao.getJobsPage(request.plusPage());
        if (CollectionUtil.isEmpty(jobsPage.getRecords())) {
            return PageBaseResp.empty();
        }
        return PageBaseResp.init(jobsPage,jobsPage.getRecords());
    }

}

