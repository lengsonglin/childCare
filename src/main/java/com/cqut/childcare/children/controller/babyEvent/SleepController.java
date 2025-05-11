package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.SleepDto;
import com.cqut.childcare.children.domain.entity.Sleep;
import com.cqut.childcare.children.service.babyEvent.SleepService;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 宝宝睡眠相关接口
 * @Author Faiz
 * @ClassName SleepController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/sleep")
@Api(tags = "宝宝睡眠相关接口")
public class SleepController {

    @Autowired
    private SleepService sleepService;

    @ApiOperation(value = "添加睡眠记录")
    @PostMapping(value = "/addSleepRecord")
    public ApiResult addSleepRecord(@Valid @RequestBody SleepDto sleepDto) {
        Long cid = RequestHolder.get().getCid();
        sleepService.addSleepRecord(cid, sleepDto);
        return ApiResult.success();
    }

    @ApiOperation(value = "查看睡眠记录")
    @PostMapping(value = "/getSleepRecord")
    public ApiResult<List<Sleep>> getSleepRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(sleepService.getSleepRecord(cid, periodTimeBaseReq));
    }

    @ApiOperation(value = "根据id查看睡眠记录")
    @GetMapping(value = "/getSleepRecord/{recordId}")
    public ApiResult<Sleep> getSleepRecordOne(@PathVariable Long recordId) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(sleepService.getSleepRecordOne(recordId));
    }

    @ApiOperation(value = "修改睡眠记录")
    @PostMapping(value = "/modifySleepRecord/{sleepId}")
    public ApiResult modifySleepRecord(@Valid @RequestBody SleepDto sleepDto, @PathVariable Long sleepId) {
        Long cid = RequestHolder.get().getCid();
        return sleepService.modifySleepRecord(cid, sleepDto, sleepId);
    }

    @ApiOperation(value = "删除睡眠记录")
    @DeleteMapping(value = "/deleteSleepRecord/{sleepId}")
    public ApiResult deleteSleepRecord(@PathVariable Long sleepId) {
        Long cid = RequestHolder.get().getCid();
        return sleepService.deleteSleepRecord(cid, sleepId);
    }
} 