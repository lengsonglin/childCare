package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.common.domain.dto.BasePeriodTimeReq2;
import com.cqut.childcare.system.domain.dto.DietPlanDto;
import com.cqut.childcare.system.domain.entity.DietPlan;
import com.cqut.childcare.system.domain.vo.DietPlanVo;

import java.time.LocalDate;
import java.util.List;

public interface IDietPlanService extends IService<DietPlan> {
    /**
     * 保存饮食计划
     */
    boolean saveDietPlan(DietPlanDto dietPlanDto);

    /**
     * 批量保存饮食计划
     */
    boolean saveDietPlans(List<DietPlanDto> dietPlanDtos);

    /**
     * 根据日期和餐次删除饮食计划
     */
    boolean removeByDateAndMealTime(LocalDate date, String mealTime);

    /**
     * 根据日期、餐次和食物ID列表删除多个食物
     */
    void updateByDietPlanId(DietPlanDto dietPlanDto);

    /**
     * 获取指定日期的饮食计划
     */
    List<DietPlanVo> getByDate(LocalDate date);

    /**
     * 获取指定周的饮食计划
     */
    List<DietPlanVo> getByWeek(LocalDate startDate);

    List<DietPlanVo> getByPeriodTime(BasePeriodTimeReq2 basePeriodTimeReq);
} 