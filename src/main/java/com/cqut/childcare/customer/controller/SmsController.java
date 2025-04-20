package com.cqut.childcare.customer.controller;


import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.customer.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Faiz
 * @ClassName SmsController
 * @Version 1.0
 */
@RestController
@RequestMapping("api/sms")
@Api(value = "验证码接口",tags = {"短信验证码相关API"})
public class SmsController {
    @Autowired
    private SmsService smsService ;

    @ApiOperation(value = "通过手机号码发送短信验证码")
    @GetMapping(value = "/public/sendCaptcha/{phone}")
    public ApiResult sendCaptcha(@PathVariable String phone) {
        smsService.sendCaptcha(phone);
        return ApiResult.success();
    }


}
