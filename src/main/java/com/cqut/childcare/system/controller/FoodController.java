package com.cqut.childcare.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.system.domain.entity.Food;
import com.cqut.childcare.system.domain.entity.Vaccine;
import com.cqut.childcare.system.service.IFoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
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
    public ResponseEntity deleteFood(@PathVariable Long id) {
        try {
            foodService.removeById(id);
        } catch (DataIntegrityViolationException e) {
            Throwable rootCause = e.getRootCause();
            if (rootCause instanceof SQLIntegrityConstraintViolationException ) {
               return ResponseEntity.badRequest().body("饮食计划中包含这个食物，不能直接删除！");
            }
            return ResponseEntity.badRequest().body("删除失败");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("删除失败");
        }
        return ResponseEntity.ok().build();
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
    public ResponseEntity<PageBaseResp<Food>> getFoodList(@Valid PageBaseReq request) {
        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
        Page<Food> page = foodService.page(request.plusPage(), queryWrapper);
        return ResponseEntity.ok(PageBaseResp.init(page, page.getRecords()));
    }

    @GetMapping("/all")
    @ApiOperation("获取全部食物")
    public ResponseEntity<List<Food>> getAll() {
        return ResponseEntity.ok(foodService.list());
    }
}
