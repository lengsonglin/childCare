package com.cqut.childcare.customer.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author Faiz
 * @ClassName AddFamilyDto
 * @Version 1.0
 */
@Data
public class AddFamilyDto {
    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("电话")
    private String telPhone;
}
