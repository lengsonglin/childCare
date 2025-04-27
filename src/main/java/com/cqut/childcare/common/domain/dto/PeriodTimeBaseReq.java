package com.cqut.childcare.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Description
 * @Author Faiz
 * @ClassName PeriodTimeBaseReq
 * @Version 1.0
 */
@Data
@ApiModel("基础时段请求(宝宝事件记录查看)")
public class PeriodTimeBaseReq {
    @NotNull(message = "宝宝Id不能为空")
    @ApiModelProperty("宝宝Id")
    private Long babyId;
    @ApiModelProperty("开始时间")
    private LocalDate beginTime;
    @ApiModelProperty("结束时间")
    private LocalDate endTime;

}
