package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Faiz
 * @ClassName DietPlanFoods
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_diet_plan_food")
public class DietPlanFood {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long foodId;
    private Long dietPlanId;

}
