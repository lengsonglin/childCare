package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.DevelopmentCheckRecordDto;
import com.cqut.childcare.children.domain.entity.DevelopmentCheckRecord;
import com.cqut.childcare.children.service.babyEvent.DevelopmentCheckRecordService;
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
 * @Description 宝宝发展检核相关接口
 * @Author Faiz
 * @ClassName DevelopmentCheckRecordController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/development-check")
@Api(tags = "宝宝发展检核相关接口")
public class DevelopmentCheckRecordController {

    @Autowired
    private DevelopmentCheckRecordService developmentCheckRecordService;

    @ApiOperation(value = "添加发展检核记录")
    @PostMapping(value = "/addDevelopmentCheckRecord")
    public ApiResult addDevelopmentCheckRecord(@Valid @RequestBody DevelopmentCheckRecordDto developmentCheckRecordDto) {
        Long cid = RequestHolder.get().getCid();
        developmentCheckRecordService.addDevelopmentCheckRecord(cid, developmentCheckRecordDto);
        return ApiResult.success();
    }

    @ApiOperation(value = "查看发展检核记录")
    @PostMapping(value = "/getDevelopmentCheckRecord")
    public ApiResult<List<DevelopmentCheckRecord>> getDevelopmentCheckRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(developmentCheckRecordService.getDevelopmentCheckRecord(cid, periodTimeBaseReq));
    }

    @ApiOperation(value = "修改发展检核记录")
    @PostMapping(value = "/modifyDevelopmentCheckRecord/{recordId}")
    public ApiResult modifyDevelopmentCheckRecord(@Valid @RequestBody DevelopmentCheckRecordDto developmentCheckRecordDto, @PathVariable Long recordId) {
        Long cid = RequestHolder.get().getCid();
        return developmentCheckRecordService.modifyDevelopmentCheckRecord(cid, developmentCheckRecordDto, recordId);
    }

    @ApiOperation(value = "删除发展检核记录")
    @DeleteMapping(value = "/deleteDevelopmentCheckRecord/{recordId}")
    public ApiResult deleteDevelopmentCheckRecord(@PathVariable Long recordId) {
        Long cid = RequestHolder.get().getCid();
        return developmentCheckRecordService.deleteDevelopmentCheckRecord(cid, recordId);
    }

    @ApiOperation(value = "根据id查询发展检核记录")
    @GetMapping(value = "/getDevelopmentCheckRecordOne/{recordId}")
    public ApiResult<DevelopmentCheckRecord> getDevelopmentCheckRecordOne(@PathVariable Long recordId) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(developmentCheckRecordService.getDevelopmentCheckRecordOne(recordId));
    }
} 