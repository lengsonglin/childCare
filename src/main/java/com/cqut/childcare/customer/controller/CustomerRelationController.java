package com.cqut.childcare.customer.controller;

import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.utils.RequestHolder;
import com.cqut.childcare.customer.domain.dto.AddFamilyDto;
import com.cqut.childcare.customer.domain.vo.CustomerBaseInfo;
import com.cqut.childcare.customer.service.CustomerInfoService;
import com.cqut.childcare.customer.service.CustomerRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerRelationController
 * @Version 1.0
 */
@Api(tags = "用户关系相关接口")
@RestController
@RequestMapping("/api/customerRelation")
public class CustomerRelationController {

    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private CustomerRelationService customerRelationService;
    @ApiOperation(value = "根据电话号码查询用户")
    @GetMapping("/getCustomerInfoByTelPhone")
    public ApiResult getCurrentUserInfo(String telPhone) {
        return customerInfoService.getCustomerInfoByTelPhone(telPhone);
    }
    @ApiOperation(value = "添加家人(只允许绑定宝宝第一个家长(主家长)去添加其他家人)")
    @PostMapping("/addFamily")
    public ApiResult addFamily(@RequestBody AddFamilyDto addFamilyDto ){
        Long cid = RequestHolder.get().getCid();
        return customerRelationService.addFamily(cid,addFamilyDto);

    }

    @ApiOperation(value = "获取绑定的相关家长")
    @GetMapping("/getFamily")
    public ApiResult<List<CustomerBaseInfo>> getFamily(){
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(customerRelationService.getFamily(cid));
    }


}
