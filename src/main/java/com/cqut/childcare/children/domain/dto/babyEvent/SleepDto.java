package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 睡眠记录DTO
 * @Author Faiz
 * @ClassName SleepDto
 * @Version 1.0
 */
@Data
public class SleepDto {
    @ApiModelProperty("宝宝id")
    @NotNull(message = "babyId不能为空")
    private Long babyId;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始时间不能为空")
    private Date beginTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "结束时间不能为空")
    private Date endTime;

    @ApiModelProperty("睡眠状态")
    @NotEmpty(message = "睡眠状态不能为空")
    private String state;

    @ApiModelProperty("备注")
    private String remark;
} 