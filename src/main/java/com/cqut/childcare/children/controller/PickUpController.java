package com.cqut.childcare.children.controller;

import com.cqut.childcare.children.domain.entity.PickUpInfo;
import com.cqut.childcare.children.service.PickUpInfoService;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Faiz
 * @ClassName PickUpController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/pickUpInfo")
@Api(tags = "宝宝代接者信息")
public class PickUpController {
    @Autowired
    private PickUpInfoService pickUpInfoService;

    @ApiOperation(value = "指定宝宝的代接人")
    @PostMapping(value = "/pickUpInfo")
    public ApiResult pickUpInfo(@RequestBody PickUpInfo pickUpInfo){
        Long cid = RequestHolder.get().getCid();
        pickUpInfoService.addOrUpdate(cid,pickUpInfo);
        return ApiResult.success();
    }

    @ApiOperation(value = "获取宝宝的代接人信息")
    @GetMapping(value = "/getPickUpInfo/{babyId}")
    public ApiResult getPickUpInfo(@PathVariable Long babyId){
        PickUpInfo pickUpInfo = pickUpInfoService.getPickUpInfo(babyId);
        return ApiResult.success(pickUpInfo);
    }
}
