package com.cqut.childcare.customer.controller;


import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import com.cqut.childcare.customer.domain.dto.CustomerLoginAboutDto;
import com.cqut.childcare.customer.domain.dto.CustomerLoginDto;
import com.cqut.childcare.customer.domain.dto.ModifyCInfoDto;
import com.cqut.childcare.customer.domain.vo.CustomerBaseInfo;
import com.cqut.childcare.customer.domain.vo.CustomerInfoVo;
import com.cqut.childcare.customer.domain.vo.LoginVo;
import com.cqut.childcare.customer.service.CustomerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerController
 * @Version 1.0
 */
@Api(value = "用户相关接口"  ,tags = {"用户相关接口"})
@RestController
@RequestMapping("api/customer")
public class CustomerInfoController {
    @Autowired
    private CustomerInfoService customerInfoService;

    @ApiOperation(value = "用户注册或修改密码")
    @PostMapping("public/loginAbout")
    public ApiResult<Void> loginAbout(@RequestBody CustomerLoginAboutDto customerLoginAboutDto){
        customerInfoService.loginAbout(customerLoginAboutDto);
        return ApiResult.success();
    }
    @ApiOperation(value = "用户登录")
    @PostMapping("public/login")
    public ApiResult<LoginVo> login(@RequestBody CustomerLoginDto customerLoginDto){
        return customerInfoService.login(customerLoginDto);
    }

    @ApiOperation(value = "获取用户id获取用户基本信息")
    @GetMapping("/{cId}/getCustomerInfo")
    public ApiResult<CustomerBaseInfo> getCurrentUserInfo(@PathVariable Long cId) {
        return ApiResult.success(customerInfoService.getCustomerInfo(cId));
    }
    @ApiOperation("刷新即将失效的token")
    @GetMapping("/renewalToken")
    public ApiResult<Void> renewalTokenIfNecessary(){
        Long cid = RequestHolder.get().getCid();
        customerInfoService.renewalTokenIfNecessary(cid);
        return ApiResult.success();
    }
    @ApiOperation("修改用户信息")
    @PostMapping("/modifyCInfo")
    public ApiResult<CustomerInfoVo> modifyCInfoDto(@Valid @ModelAttribute ModifyCInfoDto modifyCInfoDto){
        Long cid = RequestHolder.get().getCid();
        CustomerInfoVo customerInfoVo = customerInfoService.modifyCustomerInfo(cid,modifyCInfoDto);
        return ApiResult.success(customerInfoVo);
    }
    @ApiOperation("获取用户头像")
    @GetMapping("/getCAvatar")
    public ApiResult<Map<String,String>> getCustomerAvatar(@RequestParam(name = "用户头像名") String avatar){
        return customerInfoService.getCustomerAvatar(avatar);
    }

}
