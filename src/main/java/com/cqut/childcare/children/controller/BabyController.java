package com.cqut.childcare.children.controller;

import com.cqut.childcare.children.domain.dto.AddBabyDto;
import com.cqut.childcare.children.domain.dto.BabyDto;
import com.cqut.childcare.children.domain.dto.CreateBabyDto;
import com.cqut.childcare.children.domain.entity.Baby;
import com.cqut.childcare.children.domain.vo.BabyDetailVo;
import com.cqut.childcare.children.domain.vo.BabyVo;
import com.cqut.childcare.children.service.BabyService;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby")
@Api(tags = "宝宝管理相关接口")
public class BabyController {

    @Autowired
    private BabyService babyService;
    @ApiOperation(value = "家长添加宝宝")
    @PostMapping(value = "/family/addBaby",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult createBaby(@Valid @ModelAttribute CreateBabyDto babyCreateDto){
        Long cid = RequestHolder.get().getCid();
        babyService.createBaby(babyCreateDto,cid);
        return ApiResult.success();
    }

    @ApiOperation(value = "修改宝宝信息")
    @PostMapping(value = "/modifyBabyInfo")
    public ApiResult modifyBabyInfo(@Valid @ModelAttribute BabyDto babyDto){
        Long cid = RequestHolder.get().getCid();
        babyService.modifyBabyInfo(babyDto);
        return ApiResult.success();
    }



    @ApiOperation(value = "托育员添加宝宝")
    @PostMapping(value = "/childWorker/addBaby")
    public ApiResult addBaby(@Valid @RequestBody AddBabyDto addBabyDto){
        Long cid = RequestHolder.get().getCid();
        babyService.addBaby(addBabyDto,cid);
        return ApiResult.success();
    }

    @ApiOperation(value = "解绑与宝宝关系")
    @PostMapping(value = "/unbindBaby/{babyId}")
    public ApiResult unbindBaby(@PathVariable Long babyId){
        Long cid = RequestHolder.get().getCid();
        babyService.unbindBaby(cid,babyId);
        return ApiResult.success();
    }
    @ApiOperation(value = "获取当前用户关联的宝宝")
    @GetMapping(value = "/getRelatedBaby")
    public ApiResult<List<BabyVo>> getRelatedBaby(){
        Long cid = RequestHolder.get().getCid();
        List<BabyVo> data = babyService.getRelatedBaby(cid);
        return ApiResult.success(data);
    }
    @ApiOperation(value = "根据id获取宝宝详细信息")
    @GetMapping(value = "/BabyInfo/{babyId}")
    public ApiResult<BabyDetailVo> getBabyDetailInfo(@PathVariable Long babyId){
        Long cid = RequestHolder.get().getCid();
        BabyDetailVo data = babyService.getBabyDetailInfo(babyId);
        return ApiResult.success(data);
    }





}
