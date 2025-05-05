package com.cqut.childcare.children.controller;

import com.cqut.childcare.children.service.babyEvent.*;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.BabyEventEnum;
import com.cqut.childcare.common.utils.RequestHolder;
import com.cqut.childcare.common.exception.CommonErrorEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyEventController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/babyEvent")
@Api(tags = "宝宝管理相关接口")
public class BabyEventController {

    @Autowired
    private CleanService cleanService;
    @Autowired
    private DiningService diningService;
    @Autowired
    private SleepService sleepService;
    @Autowired
    private DevelopmentCheckRecordService developmentCheckRecordService;
    @Autowired
    private GrowthRecordService growthRecordService;
    @Autowired
    private SickService sickService;
    @Autowired
    private TeethService teethService;
    @Autowired
    private VaccinationService vaccinationService;

    @ApiOperation(value = "查看所有事件记录")
    @PostMapping(value = "/getAllEventRecord")
    public ApiResult<Map<String, List<?>>> getAllEventRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();

        // 1. 时间参数处理
        LocalDate begin = periodTimeBaseReq.getBeginTime();
        LocalDate end = periodTimeBaseReq.getEndTime();
        if (begin == null || end == null) {
            end = LocalDate.now();
            begin = end.minusDays(6); // 最近7天
        }
        long days = java.time.temporal.ChronoUnit.DAYS.between(begin, end);
        if (days > 6) { // 7天跨度，天数差为6
            return ApiResult.fail(BabyEventEnum.PERIOD_TIME_OUT);
        }

        periodTimeBaseReq.setBeginTime(begin);
        periodTimeBaseReq.setEndTime(end);

        Map<String, List<?>> result = new HashMap<>();
        result.put("clean", cleanService.getCleanRecord(cid, periodTimeBaseReq));
        result.put("dining", diningService.getDiningRecord(cid, periodTimeBaseReq));
        result.put("sleep", sleepService.getSleepRecord(cid, periodTimeBaseReq));
        result.put("developmentCheckRecord", developmentCheckRecordService.getDevelopmentCheckRecord(cid, periodTimeBaseReq));
        result.put("growthRecord", growthRecordService.getGrowthRecord(cid, periodTimeBaseReq));
        result.put("sick", sickService.getSickRecord(cid, periodTimeBaseReq));
        result.put("teeth", teethService.getTeethRecord(cid, periodTimeBaseReq));
        result.put("vaccination", vaccinationService.getVaccinationRecord(cid, periodTimeBaseReq));

        return ApiResult.success(result);
    }
}
