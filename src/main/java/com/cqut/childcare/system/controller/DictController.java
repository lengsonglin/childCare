package com.cqut.childcare.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.system.domain.entity.DictKey;
import com.cqut.childcare.system.domain.entity.dictValue;
import com.cqut.childcare.system.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 字典管理控制器
 * @Author Faiz
 * @ClassName DictController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/system/dict")
@Api(tags = "字典管理接口")
public class DictController {

    @Autowired
    private DictService dictService;

    @PostMapping("/key/add")
    @ApiOperation("添加字典键")
    public ResponseEntity<Void> addDictKey(@RequestBody DictKey dictKey) {
        dictService.addDictKey(dictKey);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/key/update")
    @ApiOperation("更新字典键")
    public ResponseEntity<Void> updateDictKey(@RequestBody DictKey dictKey) {
        dictService.updateDictKey(dictKey);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/key/delete/{id}")
    @ApiOperation("删除字典键")
    public ResponseEntity<Void> deleteDictKey(@PathVariable Long id) {
        dictService.deleteDictKey(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/value/add")
    @ApiOperation("添加字典值")
    public ResponseEntity<Void> addDictValue(@RequestBody dictValue dictValue) {
        dictService.addDictValue(dictValue);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/value/update")
    @ApiOperation("更新字典值")
    public ResponseEntity<Void> updateDictValue(@RequestBody dictValue dictValue) {
        dictService.updateDictValue(dictValue);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/value/delete/{id}")
    @ApiOperation("删除字典值")
    public ResponseEntity<Void> deleteDictValue(@PathVariable Long id) {
        dictService.deleteDictValue(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/key/list")
    @ApiOperation("获取字典键列表")
    public ResponseEntity<PageBaseResp<DictKey>> listDictKeys(@Valid PageBaseReq request) {
        Page<DictKey> page = dictService.page(request.plusPage());
        return ResponseEntity.ok(PageBaseResp.init(page, page.getRecords()));
    }

    @GetMapping("/key/{id}")
    @ApiOperation("获取字典键详情")
    public ResponseEntity<DictKey> getDictKey(@PathVariable Long id) {
        DictKey dictKey = dictService.getById(id);
        return ResponseEntity.ok(dictKey);
    }

    @GetMapping("/value/list/{dictKeyId}")
    @ApiOperation("根据字典键获取字典值列表")
    public ResponseEntity<List<dictValue>> listDictValuesByKeyId(@PathVariable Long dictKeyId) {
        List<dictValue> dictValues = dictService.listDictValuesByKeyId(dictKeyId);
        return ResponseEntity.ok(dictValues);
    }
} 