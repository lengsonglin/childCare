package com.cqut.childcare.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.system.domain.entity.DevelopmentCheck;
import com.cqut.childcare.system.service.DevelopmentCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * @Description 发育检查控制器
 * @Author Faiz
 * @ClassName DevelopmentCheckController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/system/development-check")
@Api(tags = "发育检查接口")
public class DevelopmentCheckController {

    @Autowired
    private DevelopmentCheckService developmentCheckService;

    @PostMapping("/add")
    @ApiOperation("添加发育检查记录")
    public ResponseEntity<Void> addDevelopmentCheck(@RequestBody DevelopmentCheck developmentCheck) {
        developmentCheckService.addDevelopmentCheck(developmentCheck);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    @ApiOperation("更新发育检查记录")
    public ResponseEntity<Void> updateDevelopmentCheck(@RequestBody DevelopmentCheck developmentCheck) {
        developmentCheckService.updateDevelopmentCheck(developmentCheck);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除发育检查记录")
    public ResponseEntity<Void> deleteDevelopmentCheck(@PathVariable Long id) {
        developmentCheckService.deleteDevelopmentCheck(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @ApiOperation("获取发育检查列表")
    public ResponseEntity<PageBaseResp<DevelopmentCheck>> listDevelopmentChecks(@Valid PageBaseReq request) {
        Page<DevelopmentCheck> page = developmentCheckService.page(request.plusPage());
        return ResponseEntity.ok(PageBaseResp.init(page,page.getRecords()));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取发育检查详情")
    public ResponseEntity<DevelopmentCheck> getDevelopmentCheck(@PathVariable Long id) {
        DevelopmentCheck developmentCheck = developmentCheckService.getById(id);
        return ResponseEntity.ok(developmentCheck);
    }
} 