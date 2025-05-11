package com.cqut.childcare.system.controller;

import com.cqut.childcare.common.domain.dto.PageBaseReq;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.customer.domain.vo.CustomerInfoVo;
import com.cqut.childcare.system.domain.dto.ChildcareWorkerDto;
import com.cqut.childcare.system.service.ChildcareWorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description t托育人员管理
 * @Author Faiz
 * @ClassName ChildcareWorkerController
 * @Version 1.0
 */
@Api(tags = "托育人员接口")
@RestController
@RequestMapping("/api/system/childcareWorker")
public class ChildcareWorkerController {

    @Autowired
    private ChildcareWorkerService childcareWorkerService;
    @GetMapping("/getWorkerInfo")
    @ApiOperation("获取托育人员信息(可根据真实姓名查询)")
    public ResponseEntity<PageBaseResp<CustomerInfoVo>> getAllChildcareWorker(@Valid PageBaseReq request,@RequestParam(required = false) String realName){
        return childcareWorkerService.getChildcareWorkerPage(request.plusPage(),realName);
    }

    @PostMapping("/addWorker")
    @ApiOperation("添加托育人员")
    public ResponseEntity<?> addChildcareWorker(@RequestBody ChildcareWorkerDto childcareWorkerDto){
        int count = childcareWorkerService.addChildcareWorker(childcareWorkerDto);
        if (count>0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("添加托育人员失败！");
    }
    @DeleteMapping("/deleteWorker/{id}")
    @ApiOperation("根据id删除托育员")
    public ResponseEntity<?> deleteChildcareWorker(@PathVariable Long id){
        childcareWorkerService.deleteChildcareWorkerById(id);
        return ResponseEntity.ok().build();
    }
}
