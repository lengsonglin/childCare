package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 发展检核记录DTO
 * @Author Faiz
 * @ClassName DevelopmentCheckRecordDto
 * @Version 1.0
 */
@Data
public class DevelopmentCheckRecordDto {
    @ApiModelProperty("宝宝id")
    @NotNull(message = "babyId不能为空")
    private Long babyId;

    @ApiModelProperty("发展检核项目id")
    @NotNull(message = "发展检核项目id不能为空")
    private Long developmentCheckId;

    @ApiModelProperty("检核时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "检核时间不能为空")
    private Date time;

    @ApiModelProperty("是否完成")
    @NotNull(message = "是否完成不能为空")
    private Byte isComplete;

    @ApiModelProperty("备注")
    private String remark;
} 