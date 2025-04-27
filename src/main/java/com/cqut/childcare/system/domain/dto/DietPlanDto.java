package com.cqut.childcare.system.domain.dto;

import com.cqut.childcare.system.domain.entity.Food;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName DietPlanDto
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class DietPlanDto {


    @ApiModelProperty("具体哪一天")
    @JsonFormat(pattern = "")
    private LocalDate timeDay;

    @ApiModelProperty("哪个具体时段名(如早餐，午餐)")
    private String timeName;

    @ApiModelProperty("食物id")
    private Long[] ids;

}
