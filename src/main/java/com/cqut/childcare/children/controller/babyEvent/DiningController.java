package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.CleanDto;
import com.cqut.childcare.children.domain.dto.babyEvent.DiningDto;
import com.cqut.childcare.children.domain.entity.Clean;
import com.cqut.childcare.children.domain.entity.Dining;
import com.cqut.childcare.children.service.babyEvent.DiningService;
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
 * @ClassName DiningController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/dining")
@Api(tags = "宝宝用餐相关接口")
public class DiningController {
    @Autowired
    private DiningService diningService;

    @ApiOperation(value = "添加用餐记录")
    @PostMapping(value = "/addDiningRecord")
    public ApiResult addCleanRecord(@Valid @ModelAttribute DiningDto diningDto){
        Long cid = RequestHolder.get().getCid();
        diningService.addDiningRecord(cid,diningDto);
        return ApiResult.success();
    }

    @ApiOperation(value = "查看用餐记录")
    @PostMapping(value = "/getDiningRecord")
    public ApiResult<List<Dining>> getDiningRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq){
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(diningService.getDiningRecord(cid,periodTimeBaseReq));
    }

    @ApiOperation(value = "修改用餐记录")
    @PostMapping(value = "/modifyDiningRecord/{diningId}")
    public ApiResult modifyDiningRecord(@Valid @ModelAttribute DiningDto diningDto, @PathVariable Long diningId){
        Long cid = RequestHolder.get().getCid();
        return diningService.modifyDiningRecord(cid,diningDto,diningId);
    }
    @ApiOperation(value = "修改用餐记录")
    @DeleteMapping(value = "/deleteCleanRecord/{diningId}")
    public ApiResult deleteDiningRecord(@PathVariable Long diningId){
        Long cid = RequestHolder.get().getCid();
        return diningService.deleteDiningRecord(cid,diningId);
    }


}
