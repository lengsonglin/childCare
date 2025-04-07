package com.cqut.childcare.quartz.controller;

import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.quartz.Service.JobService;
import com.cqut.childcare.quartz.domain.QrtzJob;
import com.cqut.childcare.quartz.domain.QrtzJobDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @Description
 * @Author Faiz
 * @ClassName JobController
 * @Version 1.0
 */
@RestController
@Api(tags = "定时任务相关接口")
@RequestMapping("/api/jobs")
@Validated
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/addJob")
    @ApiOperation("添加定时任务")
    public ResponseEntity<?> createJob(@RequestBody QrtzJobDto qrtzJobDto) {
        QrtzJob qrtzJob = new QrtzJob();
        BeanUtils.copyProperties(qrtzJobDto,qrtzJob);
        try {
            jobService.addJob(qrtzJob);
            return ResponseEntity.ok("任务创建成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/pause")
    @ApiOperation("暂停指定定时任务")
    public ResponseEntity<?> pauseJob(@PathVariable Long id) {
        QrtzJob qrtzJob = jobService.getById(id);
        if (Objects.isNull(qrtzJob)) {
            return ResponseEntity.badRequest().body("任务不存在！");
        }
        if (qrtzJob.getStatus().equals("PAUSED")){
            return ResponseEntity.ok("任务已暂停,请勿重复操作");
        }
        jobService.pauseJob(qrtzJob.getJobName());
        return ResponseEntity.ok("任务已暂停");
    }

    @PatchMapping("/{id}/run")
    @ApiOperation("启动指定定时任务")
    public ResponseEntity<?> resumeJob(@PathVariable Long id) {
        QrtzJob qrtzJob = jobService.getById(id);
        if (Objects.isNull(qrtzJob)) {
            return ResponseEntity.badRequest().body("任务不存在！");
        }
        if (qrtzJob.getStatus().equals("RUNNING")){
            return ResponseEntity.ok("任务已启动,请勿重复操作");
        }
        jobService.resumeJob(qrtzJob.getJobName());
        return ResponseEntity.ok("任务已启动");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除指定定时任务")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        QrtzJob qrtzJob = jobService.getById(id);
        if (Objects.isNull(qrtzJob)) {
            return ResponseEntity.badRequest().body("任务不存在！");
        }
        jobService.deleteJob(qrtzJob.getJobName());
        return ResponseEntity.ok("任务已删除");
    }

    @GetMapping("/getJobs")
    @ApiOperation("分页获取定时任务")
    public ResponseEntity<PageBaseResp<QrtzJob>> getJobs(@Valid PageBaseReq request){
        return ResponseEntity.ok(jobService.getJobs(request));
    }

    @PatchMapping("/{id}/execute")
    @ApiOperation("立即执行一次当前任务")
    public ResponseEntity<?> executeJob(@PathVariable Long id){
        QrtzJob qrtzJob = jobService.getById(id);
        if (Objects.isNull(qrtzJob)) {
            return ResponseEntity.badRequest().body("任务不存在！");
        }
        try {
            jobService.executeJob(qrtzJob.getJobName());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("任务执行成功");
    }

}

