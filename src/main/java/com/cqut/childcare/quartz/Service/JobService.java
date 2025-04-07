package com.cqut.childcare.quartz.Service;


import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.quartz.domain.QrtzJob;
import org.quartz.SchedulerException;

/**
 * @Description
 * @Author Faiz
 * @ClassName JobService
 * @Version 1.0
 */

public interface JobService {


    void addJob(QrtzJob qrtzjob) throws SchedulerException;

    QrtzJob getById(Long id);

    void pauseJob(String jobName);

    void deleteJob(String jobName);

    void resumeJob(String jobName);

    PageBaseResp<QrtzJob> getJobs(PageBaseReq request);

    void executeJob(String jobName);
}
