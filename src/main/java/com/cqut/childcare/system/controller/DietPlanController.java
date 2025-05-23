package com.cqut.childcare.system.controller;

import com.cqut.childcare.common.domain.dto.BasePeriodTimeReq2;
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
import java.util.ArrayList;
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

    @PostMapping("addDietPlan")
    @ApiOperation("添加饮食计划")
    public ResponseEntity<Boolean> addDietPlan(@RequestBody DietPlanDto dietPlanDto) {
        return ResponseEntity.ok(dietPlanService.saveDietPlan(dietPlanDto));
    }

    @PostMapping("/addDietPlan/batch")
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

    @PostMapping("/updateByDietPlanId")
    @ApiOperation("根据id更新饮食计划")
    public ResponseEntity updateByDietPlanId(@RequestBody DietPlanDto dietPlanDto) {
        dietPlanService.updateByDietPlanId(dietPlanDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("根据id删除饮食计划")
    public ResponseEntity deleteByDietPlanId(@PathVariable Long id) {
        dietPlanService.deleteByDietPlanId(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/day")
    @ApiOperation("获取指定日期的饮食计划")
    public ResponseEntity<List<DietPlanVo>> getDietPlanByDate(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(dietPlanService.getByDate(parsedDate));
    }

    @GetMapping("/week")
    @ApiOperation("获取指定日期这周的饮食计划")
    public ResponseEntity<List<DietPlanVo>> getDietPlanByWeek(String inputDate) {
        LocalDate parsedDate = LocalDate.parse(inputDate);
        return ResponseEntity.ok(dietPlanService.getByWeek(parsedDate));
    }
    @PostMapping("/getByPeriodTime")
    @ApiOperation("获取开始日期到结束日期的饮食计划")
    public ResponseEntity<List<DietPlanVo>> getDietPlanPeriodTime(@RequestBody BasePeriodTimeReq2 basePeriodTimeReq){
        return ResponseEntity.ok(dietPlanService.getByPeriodTime(basePeriodTimeReq));
    }
}
