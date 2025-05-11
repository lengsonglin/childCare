package com.cqut.childcare.system.domain.vo;

import com.cqut.childcare.system.domain.entity.Food;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName DietPlanVo
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class DietPlanVo {
    private Long id;
    @ApiModelProperty("具体哪一天")
    private LocalDate timeDay;

    @ApiModelProperty("哪个具体时段名(如早餐，午餐)")
    private String timeName;

    @ApiModelProperty("包含的食物列表")
    private List<Food> foods;

    @ApiModelProperty("备注")
    private String Remark;
}
