package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Description
 * @Author Faiz
 * @ClassName DietPlan
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_diet_plan")
public class DietPlan {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDate timeDay;

    private String timeName;

    private String remark;
}
