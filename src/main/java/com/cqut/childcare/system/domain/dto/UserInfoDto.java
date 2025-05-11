package com.cqut.childcare.system.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description
 * @Author Faiz
 * @ClassName UserInfoDto
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class UserInfoDto {
    @ApiModelProperty("用户id")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    private String account;

    @ApiModelProperty("真实姓名")
    @NotBlank(message = "真实姓名不能为空")
    private String name;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话号码")
    @Pattern(regexp="^1[3-9]\\d{9}$", message="手机号格式错误")
    private String telPhone;
    @ApiModelProperty("备注")
    private String remark;
}
