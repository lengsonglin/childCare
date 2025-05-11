package com.cqut.childcare.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Description
 * @Author Faiz
 * @ClassName DietPlanPeriodTimeReq
 * @Version 1.0
 */
@Data
public class BasePeriodTimeReq2 {
    @ApiModelProperty("开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("结束日期")
    private LocalDate endDate;
}
