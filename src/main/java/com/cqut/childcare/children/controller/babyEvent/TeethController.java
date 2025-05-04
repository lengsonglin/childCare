package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.TeethDto;
import com.cqut.childcare.children.domain.entity.Teeth;
import com.cqut.childcare.children.service.babyEvent.TeethService;
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
 * @Description 宝宝长牙相关接口
 * @Author Faiz
 * @ClassName TeethController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/teeth")
@Api(tags = "宝宝长牙相关接口")
public class TeethController {

    @Autowired
    private TeethService teethService;

    @ApiOperation(value = "添加长牙记录")
    @PostMapping(value = "/addTeethRecord")
    public ApiResult addTeethRecord(@Valid @RequestBody TeethDto teethDto) {
        Long cid = RequestHolder.get().getCid();
        teethService.addTeethRecord(cid, teethDto);
        return ApiResult.success();
    }

    @ApiOperation(value = "查看长牙记录")
    @PostMapping(value = "/getTeethRecord")
    public ApiResult<List<Teeth>> getTeethRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(teethService.getTeethRecord(cid, periodTimeBaseReq));
    }

    @ApiOperation(value = "修改长牙记录")
    @PostMapping(value = "/modifyTeethRecord/{teethId}")
    public ApiResult modifyTeethRecord(@Valid @RequestBody TeethDto teethDto, @PathVariable Long teethId) {
        Long cid = RequestHolder.get().getCid();
        return teethService.modifyTeethRecord(cid, teethDto, teethId);
    }

    @ApiOperation(value = "删除长牙记录")
    @DeleteMapping(value = "/deleteTeethRecord/{teethId}")
    public ApiResult deleteTeethRecord(@PathVariable Long teethId) {
        Long cid = RequestHolder.get().getCid();
        return teethService.deleteTeethRecord(cid, teethId);
    }
} 