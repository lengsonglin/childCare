package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 成长记录DTO
 * @Author Faiz
 * @ClassName GrowthRecordDto
 * @Version 1.0
 */
@Data
public class GrowthRecordDto {
    @ApiModelProperty("宝宝id")
    @NotNull(message = "babyId不能为空")
    private Long babyId;

    @ApiModelProperty("身高")
    private String height;

    @ApiModelProperty("体重")
    private String weight;

    @ApiModelProperty("头围")
    private String headCircumference;

    @ApiModelProperty("记录时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "记录时间不能为空")
    private Date time;

    private String remark;
} 