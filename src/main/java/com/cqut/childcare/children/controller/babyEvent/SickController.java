package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.SickDto;
import com.cqut.childcare.children.domain.entity.Sick;
import com.cqut.childcare.children.service.babyEvent.SickService;
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
 * @Description 宝宝生病相关接口
 * @Author Faiz
 * @ClassName SickController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/sick")
@Api(tags = "宝宝生病相关接口")
public class SickController {

    @Autowired
    private SickService sickService;

    @ApiOperation(value = "添加生病记录")
    @PostMapping(value = "/addSickRecord")
    public ApiResult addSickRecord(@Valid @RequestBody SickDto sickDto) {
        Long cid = RequestHolder.get().getCid();
        sickService.addSickRecord(cid, sickDto);
        return ApiResult.success();
    }

    @ApiOperation(value = "查看生病记录")
    @PostMapping(value = "/getSickRecord")
    public ApiResult<List<Sick>> getSickRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(sickService.getSickRecord(cid, periodTimeBaseReq));
    }

    @ApiOperation(value = "修改生病记录")
    @PostMapping(value = "/modifySickRecord/{sickId}")
    public ApiResult modifySickRecord(@Valid @RequestBody SickDto sickDto, @PathVariable Long sickId) {
        Long cid = RequestHolder.get().getCid();
        return sickService.modifySickRecord(cid, sickDto, sickId);
    }

    @ApiOperation(value = "删除生病记录")
    @DeleteMapping(value = "/deleteSickRecord/{sickId}")
    public ApiResult deleteSickRecord(@PathVariable Long sickId) {
        Long cid = RequestHolder.get().getCid();
        return sickService.deleteSickRecord(cid, sickId);
    }
} 