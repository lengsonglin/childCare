package com.cqut.childcare.system.controller;

import com.cqut.childcare.system.domain.entity.Food;
import com.cqut.childcare.system.service.IFoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName FoodController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/system/food")
@Api(tags = "食物相关接口")
public class FoodController {
    
    @Autowired
    private IFoodService foodService;

    @PostMapping
    @ApiOperation("添加食物")
    public ResponseEntity<Boolean> addFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.save(food));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除食物")
    public ResponseEntity<Boolean> deleteFood(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.removeById(id));
    }

    @PutMapping
    @ApiOperation("修改食物")
    public ResponseEntity<Boolean> updateFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.updateById(food));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取食物详情")
    public ResponseEntity<Food> getFood(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getById(id));
    }

    @GetMapping("/list")
    @ApiOperation("获取食物列表")
    public ResponseEntity<List<Food>> getFoodList() {
        return ResponseEntity.ok(foodService.list());
    }
}
