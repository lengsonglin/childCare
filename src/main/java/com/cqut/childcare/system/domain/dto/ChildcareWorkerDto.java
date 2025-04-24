package com.cqut.childcare.system.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Description
 * @Author Faiz
 * @ClassName ChildcareWorkerDto
 * @Version 1.0
 */
@Data
public class ChildcareWorkerDto {

    @NotBlank(message = "电话不能为空")
    @Pattern(regexp="^1[3-9]\\d{9}$", message="手机号格式错误")
    private String telPhone;

    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    @NotBlank(message = "密码不能为空")
    private String password;


}
