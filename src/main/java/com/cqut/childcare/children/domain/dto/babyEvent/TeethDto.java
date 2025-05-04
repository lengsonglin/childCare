package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 长牙记录DTO
 * @Author Faiz
 * @ClassName TeethDto
 * @Version 1.0
 */
@Data
public class TeethDto {
    @ApiModelProperty("宝宝id")
    @NotNull(message = "babyId不能为空")
    private Long babyId;

    @ApiModelProperty("牙齿位置")
    @NotNull(message = "牙齿位置不能为空")
    private Integer position;

    @ApiModelProperty("长牙时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "长牙时间不能为空")
    private Date time;
} 