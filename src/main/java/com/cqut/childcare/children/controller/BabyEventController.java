package com.cqut.childcare.children.controller;

import com.cqut.childcare.children.service.babyEvent.BabyEventService;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private BabyEventService babyEventService;

    @ApiOperation(value = "查看所有事件记录")
    @PostMapping(value = "/getAllEventRecord")
    public ApiResult<Map<String, Object>> getAllEventRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();
        Map<String, Object> result = babyEventService.getAllEventRecords(cid, periodTimeBaseReq);
        return ApiResult.success(result);
    }
}
