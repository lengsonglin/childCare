package com.cqut.childcare.children.controller;

import com.cqut.childcare.children.domain.dto.BabyAddDto;
import com.cqut.childcare.children.domain.entity.Baby;
import com.cqut.childcare.children.service.BabyService;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
    @ApiOperation(value = "添加宝宝")
    @PostMapping(value = "/addBaby",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult createBaby(@Valid @ModelAttribute BabyAddDto babyCreateDto){
        Long cid = RequestHolder.get().getCid();
        babyService.createBaby(babyCreateDto,cid);
        return ApiResult.success();
    }
}
