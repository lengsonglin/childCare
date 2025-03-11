package com.cqut.childcare.quartz.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.quartz.domain.QrtzJob;
import com.cqut.childcare.quartz.mapper.QrtzJobMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author Faiz
 * @ClassName QrtzJobDao
 * @Version 1.0
 */

@Service
public class QrtzJobDao extends ServiceImpl<QrtzJobMapper, QrtzJob> {

    public QrtzJob getByJobName(String jobName){
        return lambdaQuery()
                .eq(QrtzJob::getJobName,jobName)
                .one();
    }

    public boolean updateById(QrtzJob qrtzJob){
        return lambdaUpdate()
                .update(qrtzJob);
    }

    public boolean updateAchieved(String name) {

        return lambdaUpdate()
                .setSql("exe_count = exe_count + 1")
                .set(QrtzJob::getLastExeTime,LocalDateTime.now())
                .eq(QrtzJob::getJobName,name)
                .update();
    }

    public boolean upStatusByJobName(String jobName) {
        return lambdaUpdate()
                .set(QrtzJob::getStatus,"PAUSED")
                .eq(QrtzJob::getJobName,jobName)
                .update();
    }

    public boolean deleteJobByJobName(String jobName) {
        return lambdaUpdate()
                .eq(QrtzJob::getJobName,jobName)
                .remove();
    }

    public boolean resumeJobByJobName(String jobName) {
        return lambdaUpdate()
                .set(QrtzJob::getStatus,"RUNNING")
                .eq(QrtzJob::getJobName,jobName)
                .update();
    }

    public Page<QrtzJob> getJobsPage(Page page) {
        return lambdaQuery()
                .page(page);
    }
}
