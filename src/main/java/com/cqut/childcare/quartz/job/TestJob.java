package com.cqut.childcare.quartz.job;

import com.cqut.childcare.quartz.dao.QrtzJobDao;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

/**
 * @Description
 * @Author Faiz
 * @ClassName TestJob
 * @Version 1.0
 */
@Component
public class TestJob extends QuartzJobBean {

    @Autowired
    private QrtzJobDao qrtzJobDao;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        // 获取参数，执行业务逻辑
        if (!CollectionUtils.isEmpty(jobDataMap)) {
            System.out.println(jobDataMap.getString("params"));
        }
        //执行完成，更新数据。
        qrtzJobDao.updateAchieved(context.getJobDetail().getKey().getName());

    }
}
