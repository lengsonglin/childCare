package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.common.domain.dto.BasePeriodTimeReq2;
import com.cqut.childcare.system.domain.dto.DietPlanDto;
import com.cqut.childcare.system.domain.entity.DietPlan;
import com.cqut.childcare.system.domain.entity.DietPlanFood;
import com.cqut.childcare.system.domain.entity.Food;
import com.cqut.childcare.system.domain.vo.DietPlanVo;
import com.cqut.childcare.system.mapper.DietPlanMapper;
import com.cqut.childcare.system.mapper.DietPlanFoodMapper;
import com.cqut.childcare.system.mapper.FoodMapper;
import com.cqut.childcare.system.service.IDietPlanService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DietPlanServiceImpl extends ServiceImpl<DietPlanMapper, DietPlan> implements IDietPlanService {

    @Autowired
    private DietPlanFoodMapper dietPlanFoodMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Override
    @Transactional
    public boolean saveDietPlan(DietPlanDto dietPlanDto) {
        // 1. 保存饮食计划
        DietPlan dietPlan = new DietPlan();
        dietPlan.setTimeDay(dietPlanDto.getTimeDay());
        dietPlan.setTimeName(dietPlanDto.getTimeName());
        dietPlan.setRemark(dietPlanDto.getRemark());
        boolean saved = save(dietPlan);
        
        if (saved && dietPlanDto.getIds() != null && dietPlanDto.getIds().length > 0) {
            // 2. 保存饮食计划食物关联
            List<DietPlanFood> dietPlanFoods = new ArrayList<>();
            for (Long foodId : dietPlanDto.getIds()) {
                DietPlanFood dietPlanFood = new DietPlanFood();
                dietPlanFood.setDietPlanId(dietPlan.getId());
                dietPlanFood.setFoodId(foodId);
                dietPlanFoods.add(dietPlanFood);
            }
            return dietPlanFoodMapper.insertBatch(dietPlanFoods) > 0;
        }
        return saved;
    }

    @Override
    @Transactional
    public boolean saveDietPlans(List<DietPlanDto> dietPlanDtos) {
        for (DietPlanDto dietPlanDto : dietPlanDtos) {
            if (!saveDietPlan(dietPlanDto)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean removeByDateAndMealTime(LocalDate date, String mealTime) {
        // 1. 查询符合条件的饮食计划
        LambdaQueryWrapper<DietPlan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DietPlan::getTimeDay, date)
               .eq(DietPlan::getTimeName, mealTime);
        List<DietPlan> dietPlans = list(wrapper);
        
        if (dietPlans.isEmpty()) {
            return true;
        }
        
        // 2. 删除关联的食物记录
        List<Long> dietPlanIds = dietPlans.stream()
                .map(DietPlan::getId)
                .collect(Collectors.toList());
        LambdaQueryWrapper<DietPlanFood> foodWrapper = new LambdaQueryWrapper<>();
        foodWrapper.in(DietPlanFood::getDietPlanId, dietPlanIds);
        dietPlanFoodMapper.delete(foodWrapper);
        
        // 3. 删除饮食计划
        return remove(wrapper);
    }

    @Override
    @Transactional
    public void updateByDietPlanId(DietPlanDto dietPlanDto) {
        // 1. 查询饮食计划
        LambdaQueryWrapper<DietPlan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DietPlan::getId, dietPlanDto.getId());
        DietPlan dietPlan = getOne(wrapper);
        updateById(DietPlan.builder()
                .id(dietPlanDto.getId())
                .timeDay(dietPlanDto.getTimeDay())
                .timeName(dietPlanDto.getTimeName())
                .remark(dietPlanDto.getRemark())
                .build()

        );
        if (ObjectUtils.isEmpty(dietPlan)) {
            return;
        }

        // 2. 更新关联的食物记录
        LambdaQueryWrapper<DietPlanFood> foodWrapper = new LambdaQueryWrapper<>();
        foodWrapper.eq(DietPlanFood::getDietPlanId, dietPlan.getId());
        dietPlanFoodMapper.delete(foodWrapper);
        Long[] ids = dietPlanDto.getIds();
        List<DietPlanFood> data = Arrays.stream(ids).map( foodId ->
                DietPlanFood.builder()
                        .dietPlanId(dietPlanDto.getId())
                        .foodId(foodId).build())
                .collect(Collectors.toList());
        dietPlanFoodMapper.insertBatch(data);
    }

    @Override
    public List<DietPlanVo> getByDate(LocalDate date) {
        // 1. 查询指定日期饮食计划
        LambdaQueryWrapper<DietPlan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DietPlan::getTimeDay, date)
               .orderByAsc(DietPlan::getTimeName);
        List<DietPlan> dietPlans = list(wrapper);
        if (dietPlans.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 2. 查询关联的食物
        List<Long> dietPlanIds = dietPlans.stream()
                .map(DietPlan::getId)
                .collect(Collectors.toList());
        LambdaQueryWrapper<DietPlanFood> foodWrapper = new LambdaQueryWrapper<>();
        foodWrapper.in(DietPlanFood::getDietPlanId, dietPlanIds);
        List<DietPlanFood> dietPlanFoods = dietPlanFoodMapper.selectList(foodWrapper);
        
        // 3. 查询食物详情
        List<Long> foodIds = dietPlanFoods.stream()
                .map(DietPlanFood::getFoodId)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, Food> foodMap = foodMapper.selectBatchIds(foodIds).stream()
                .collect(Collectors.toMap(Food::getId, food -> food));
        
        // 4. 组装数据
        Map<Long, List<Food>> dietPlanFoodMap = dietPlanFoods.stream()
                .collect(Collectors.groupingBy(
                        DietPlanFood::getDietPlanId,
                        Collectors.mapping(
                                dietPlanFood -> foodMap.get(dietPlanFood.getFoodId()),
                                Collectors.toList()
                        )
                ));
        
        return dietPlans.stream()
                .map(dietPlan -> {
                    DietPlanVo dietPlanVo = new DietPlanVo();
                    dietPlanVo.setTimeDay(dietPlan.getTimeDay());
                    dietPlanVo.setTimeName(dietPlan.getTimeName());
                    dietPlanVo.setRemark(dietPlan.getRemark());
                    dietPlanVo.setFoods(dietPlanFoodMap.getOrDefault(dietPlan.getId(), new ArrayList<>()));
                    return dietPlanVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<DietPlanVo> getByWeek(LocalDate inputDate) {
        LocalDate beginDate = inputDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = inputDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return getByPeriodTime(beginDate,endDate);
    }

    @Override
    public List<DietPlanVo> getByPeriodTime(BasePeriodTimeReq2 basePeriodTimeReq) {
        return getByPeriodTime(basePeriodTimeReq.getBeginDate(),basePeriodTimeReq.getEndDate());
    }

    @Override
    public void deleteByDietPlanId(Long id) {
        removeById(id);
        dietPlanFoodMapper.deleteById(id);
    }

    public List<DietPlanVo> getByPeriodTime(LocalDate beginDate,LocalDate endDate){
        LambdaQueryWrapper<DietPlan> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(DietPlan::getTimeDay, beginDate, endDate)
                .orderByAsc(DietPlan::getTimeDay)
                .orderByAsc(DietPlan::getTimeName);
        List<DietPlan> dietPlans = list(wrapper);

        if (dietPlans.isEmpty()) {
            return new ArrayList<>();
        }

        // 2. 查询关联的食物
        List<Long> dietPlanIds = dietPlans.stream()
                .map(DietPlan::getId)
                .collect(Collectors.toList());
        LambdaQueryWrapper<DietPlanFood> foodWrapper = new LambdaQueryWrapper<>();
        foodWrapper.in(DietPlanFood::getDietPlanId, dietPlanIds);
        List<DietPlanFood> dietPlanFoods = dietPlanFoodMapper.selectList(foodWrapper);

        // 3. 查询食物详情
        List<Long> foodIds = dietPlanFoods.stream()
                .map(DietPlanFood::getFoodId)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, Food> foodMap = foodMapper.selectBatchIds(foodIds).stream()
                .collect(Collectors.toMap(Food::getId, food -> food));

        // 4. 组装数据
        Map<Long, List<Food>> dietPlanFoodMap = dietPlanFoods.stream()
                .collect(Collectors.groupingBy(
                        DietPlanFood::getDietPlanId,
                        Collectors.mapping(
                                dietPlanFood -> foodMap.get(dietPlanFood.getFoodId()),
                                Collectors.toList()
                        )
                ));

        return dietPlans.stream()
                .map(dietPlan -> {
                    DietPlanVo dietPlanVo = new DietPlanVo();
                    dietPlanVo.setId(dietPlan.getId());
                    dietPlanVo.setTimeDay(dietPlan.getTimeDay());
                    dietPlanVo.setTimeName(dietPlan.getTimeName());
                    dietPlanVo.setRemark(dietPlan.getRemark());
                    dietPlanVo.setFoods(dietPlanFoodMap.getOrDefault(dietPlan.getId(), new ArrayList<>()));
                    return dietPlanVo;
                })
                .collect(Collectors.toList());

    }
} 