package com.cqut.childcare.system.controller;

import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.CommonErrorEnum;
import com.cqut.childcare.system.domain.entity.systemSetting;
import com.cqut.childcare.system.service.SystemSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description
 * @Author Faiz
 * @ClassName SystemSettingController
 * @Version 1.0
 */
@Api(value = "系统设置相关接口", tags = {"系统设置相关接口"})
@RestController
@RequestMapping("api/system/setting")
public class SystemSettingController {
    @Autowired
    private SystemSettingService systemSettingService;

    @ApiOperation(value = "更新系统设置")
    @PostMapping("/update")
    public ApiResult<Void> updateSystemSetting(@Valid @RequestBody systemSetting setting) {
        boolean success = systemSettingService.updateSystemSetting(setting);
        return success ? ApiResult.success() : ApiResult.fail(CommonErrorEnum.SYSTEM_ERROR);
    }
    @ApiOperation(value = "获取系统设置")
    @GetMapping("/get")
    public ApiResult<systemSetting> getSystemSetting() {
        systemSetting setting = systemSettingService.getById(1L);
        return ApiResult.success(setting);
    }
} 