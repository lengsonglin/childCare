package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.CleanDto;
import com.cqut.childcare.children.domain.entity.Clean;
import com.cqut.childcare.children.service.babyEvent.CleanService;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CleanController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/clean")
@Api(tags = "宝宝清洁相关接口")
public class CleanController {

    @Autowired
    private CleanService cleanService;

    @ApiOperation(value = "添加清洁记录")
    @PostMapping(value = "/addCleanRecord")
    public ApiResult addCleanRecord(@Valid @RequestBody CleanDto cleanDto){
        Long cid = RequestHolder.get().getCid();
        cleanService.addCleanRecord(cid,cleanDto);
        return ApiResult.success();
    }
    @ApiOperation(value = "查看清洁记录")
    @PostMapping(value = "/getCleanRecord")
    public ApiResult<List<Clean>> getCleanRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq){
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(cleanService.getCleanRecord(cid,periodTimeBaseReq));
    }

    @ApiOperation(value = "根据id修改清洁记录")
    @PostMapping(value = "/modifyCleanRecord/{cleanId}")
    public ApiResult modifyCleanRecord(@Valid @RequestBody CleanDto cleanDto,@PathVariable Long cleanId){
        Long cid = RequestHolder.get().getCid();
        return cleanService.modifyCleanRecord(cid,cleanDto,cleanId);
    }
    @ApiOperation(value = "删除清洁记录")
    @DeleteMapping(value = "/deleteCleanRecord/{cleanId}")
    public ApiResult deleteCleanRecord(@PathVariable Long cleanId){
        Long cid = RequestHolder.get().getCid();
        return cleanService.deleteCleanRecord(cid,cleanId);
    }


}
