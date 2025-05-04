package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 疫苗接种记录DTO
 * @Author Faiz
 * @ClassName VaccinationDto
 * @Version 1.0
 */
@Data
public class VaccinationDto {
    @ApiModelProperty("宝宝id")
    @NotNull(message = "babyId不能为空")
    private Long babyId;

    @ApiModelProperty("疫苗名称")
    @NotEmpty(message = "疫苗名称不能为空")
    private String name;

    @ApiModelProperty("针次")
    @NotNull(message = "针次不能为空")
    private Integer count;

    @ApiModelProperty("接种时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "接种时间不能为空")
    private Date time;

    @ApiModelProperty("备注")
    private String remark;
} 