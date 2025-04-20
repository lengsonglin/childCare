package com.cqut.childcare.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.system.domain.entity.Function;
import com.cqut.childcare.system.service.FunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 功能管理控制器
 * @Author Faiz
 * @ClassName FunctionController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/system/function")
@Api(tags = "功能管理接口")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @PostMapping("/add")
    @ApiOperation("添加功能")
    public ResponseEntity<Void> addFunction(@RequestBody Function function) {
        functionService.addFunction(function);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    @ApiOperation("更新功能")
    public ResponseEntity<Void> updateFunction(@RequestBody Function function) {
        functionService.updateFunction(function);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除功能")
    public ResponseEntity<Void> deleteFunction(@PathVariable Long id) {
        functionService.deleteFunction(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/state/update")
    @ApiOperation("更新功能状态")
    public ResponseEntity<Void> updateFunctionState(
            @RequestParam Long id,
            @RequestParam Byte onState) {
        functionService.updateFunctionState(id, onState);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @ApiOperation("获取功能列表")
    public ResponseEntity<PageBaseResp<Function>> listFunctions(@Valid PageBaseReq request,
            @RequestParam(required = false) String name) {
        LambdaQueryWrapper<Function> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.likeRight(Function::getName, name);
        }
        Page<Function> page = functionService.page(request.plusPage(), queryWrapper);
        return ResponseEntity.ok(PageBaseResp.init(page, page.getRecords()));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取功能详情")
    public ResponseEntity<Function> getFunction(@PathVariable Long id) {
        Function function = functionService.getById(id);
        return ResponseEntity.ok(function);
    }
} 