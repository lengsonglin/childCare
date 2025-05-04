package com.cqut.childcare.children.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName AddBabyDto
 * @Version 1.0
 */
@Data
public class AddBabyDto {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "出生日期不能为空")
    private Date birthday;
}
