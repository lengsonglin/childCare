package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName CleanDto
 * @Version 1.0
 */
@Data
public class CleanDto {


    @NotNull(message = "babyId不能为空")
    private Long babyId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "记录时间不能空")
    private Date time;
    @NotEmpty(message = "清洁类型不能为空")
    private String type;

    private String description;

}
