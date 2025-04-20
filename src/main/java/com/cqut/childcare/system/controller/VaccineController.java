package com.cqut.childcare.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.system.domain.entity.Vaccine;
import com.cqut.childcare.system.service.VaccineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 疫苗信息控制器
 * @Author Faiz
 * @ClassName VaccineController
 * @Version 1.0
 */
@RestController
@RequestMapping("api/system/vaccine")
@Api(tags = "疫苗信息接口")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @PostMapping("/add")
    @ApiOperation("添加疫苗信息")
    public ResponseEntity<Void> addVaccine(@RequestBody Vaccine vaccine) {
        vaccineService.addVaccine(vaccine);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    @ApiOperation("更新疫苗信息")
    public ResponseEntity<Void> updateVaccine(@RequestBody Vaccine vaccine) {
        vaccineService.updateVaccine(vaccine);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除疫苗信息")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        vaccineService.deleteVaccine(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @ApiOperation("获取疫苗列表")
    public ResponseEntity<PageBaseResp<Vaccine>> listVaccines(@Valid PageBaseReq request,
            @RequestParam(required = false) String name) {
        LambdaQueryWrapper<Vaccine> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like(Vaccine::getName, name);
        }
        Page<Vaccine> page = vaccineService.page(request.plusPage(), queryWrapper);
        return ResponseEntity.ok(PageBaseResp.init(page, page.getRecords()));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取疫苗详情")
    public ResponseEntity<Vaccine> getVaccine(@PathVariable Long id) {
        Vaccine vaccine = vaccineService.getById(id);
        return ResponseEntity.ok(vaccine);
    }
} 