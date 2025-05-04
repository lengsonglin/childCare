package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.GrowthRecordDto;
import com.cqut.childcare.children.domain.entity.GrowthRecord;
import com.cqut.childcare.children.service.babyEvent.GrowthRecordService;
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
 * @Description 宝宝成长记录相关接口
 * @Author Faiz
 * @ClassName GrowthRecordController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/growth")
@Api(tags = "宝宝成长记录相关接口")
public class GrowthRecordController {

    @Autowired
    private GrowthRecordService growthRecordService;

    @ApiOperation(value = "添加成长记录")
    @PostMapping(value = "/addGrowthRecord")
    public ApiResult addGrowthRecord(@Valid @RequestBody GrowthRecordDto growthRecordDto) {
        Long cid = RequestHolder.get().getCid();
        growthRecordService.addGrowthRecord(cid, growthRecordDto);
        return ApiResult.success();
    }

    @ApiOperation(value = "查看成长记录")
    @PostMapping(value = "/getGrowthRecord")
    public ApiResult<List<GrowthRecord>> getGrowthRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(growthRecordService.getGrowthRecord(cid, periodTimeBaseReq));
    }

    @ApiOperation(value = "修改成长记录")
    @PostMapping(value = "/modifyGrowthRecord/{recordId}")
    public ApiResult modifyGrowthRecord(@Valid @RequestBody GrowthRecordDto growthRecordDto, @PathVariable Long recordId) {
        Long cid = RequestHolder.get().getCid();
        return growthRecordService.modifyGrowthRecord(cid, growthRecordDto, recordId);
    }

    @ApiOperation(value = "删除成长记录")
    @DeleteMapping(value = "/deleteGrowthRecord/{recordId}")
    public ApiResult deleteGrowthRecord(@PathVariable Long recordId) {
        Long cid = RequestHolder.get().getCid();
        return growthRecordService.deleteGrowthRecord(cid, recordId);
    }
}
