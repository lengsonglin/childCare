package com.cqut.childcare.customer.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerRegisterDto
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginAboutDto {
    private String telPhone;

    private String password;

    @ApiModelProperty("验证码")
    private String captcha;

    /**
     *  1: “register” 或者是  2 :“modifyPassword”
     */
    @ApiModelProperty("用户操作类型，1 表达注册,2 表示 修改密码")
    private Integer type;
}
