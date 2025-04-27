package com.cqut.childcare.system.controller;

import com.cqut.childcare.system.domain.dto.DietPlanDto;
import com.cqut.childcare.system.domain.vo.DietPlanVo;
import com.cqut.childcare.system.service.IDietPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName DietPlanController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/system/dietPlan")
@Api(tags = "饮食计划接口")
public class DietPlanController {

    @Autowired
    private IDietPlanService dietPlanService;

    @PostMapping("adDietPlan")
    @ApiOperation("添加饮食计划")
    public ResponseEntity<Boolean> addDietPlan(@RequestBody DietPlanDto dietPlanDto) {
        return ResponseEntity.ok(dietPlanService.saveDietPlan(dietPlanDto));
    }

    @PostMapping("/adDietPlan/batch")
    @ApiOperation("批量添加饮食计划")
    public ResponseEntity<Boolean> addDietPlans(@RequestBody List<DietPlanDto> dietPlanDtos) {
        return ResponseEntity.ok(dietPlanService.saveDietPlans(dietPlanDtos));
    }

    @DeleteMapping("/deleteDietPlan")
    @ApiOperation("删除指定日期和餐次的饮食计划")
    public ResponseEntity<Boolean> deleteDietPlan(String date,String mealTime) {
        LocalDate parsedDate = LocalDate.parse(date); // 手动尝试转换
        return ResponseEntity.ok(dietPlanService.removeByDateAndMealTime(parsedDate, mealTime));
    }

    @DeleteMapping("/deleteDietPlan/foods")
    @ApiOperation("删除指定日期和餐次的多个食物")
    public ResponseEntity<Boolean> deleteDietPlanFoods(@RequestBody DietPlanDto dietPlanDto) {
        return ResponseEntity.ok(dietPlanService.removeFoodsByDateAndMealTime(
            dietPlanDto.getTimeDay(), 
            dietPlanDto.getTimeName(), 
            Arrays.asList(dietPlanDto.getIds())
        ));
    }

    @GetMapping("/day")
    @ApiOperation("获取指定日期的饮食计划")
    public ResponseEntity<List<DietPlanVo>> getDietPlanByDate(String date) {
        LocalDate parsedDate = LocalDate.parse(date); // 手动尝试转换
        return ResponseEntity.ok(dietPlanService.getByDate(parsedDate));
    }

    @GetMapping("/week")
    @ApiOperation("获取指定周的开始日期到结束日期的饮食计划")
    public ResponseEntity<List<DietPlanVo>> getDietPlanByWeek(String startDate) {
        LocalDate parsedDate = LocalDate.parse(startDate); // 手动尝试转换
        return ResponseEntity.ok(dietPlanService.getByWeek(parsedDate));
    }
}
