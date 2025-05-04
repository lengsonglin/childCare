package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 生病记录DTO
 * @Author Faiz
 * @ClassName SickDto
 * @Version 1.0
 */
@Data
public class SickDto {
    @ApiModelProperty("宝宝id")
    @NotNull(message = "babyId不能为空")
    private Long babyId;

    @ApiModelProperty("生病时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "生病时间不能为空")
    private Date time;

    @ApiModelProperty("生病类型")
    @NotEmpty(message = "生病类型不能为空")
    private String type;

    @ApiModelProperty("症状")
    private String symptom;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("体温")
    private String temperature;

    @ApiModelProperty("备注")
    private String remark;
} 