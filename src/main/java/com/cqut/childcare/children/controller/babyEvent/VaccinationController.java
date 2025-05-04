package com.cqut.childcare.children.controller.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.VaccinationDto;
import com.cqut.childcare.children.domain.entity.Vaccination;
import com.cqut.childcare.children.service.babyEvent.VaccinationService;
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
 * @Description 宝宝疫苗接种相关接口
 * @Author Faiz
 * @ClassName VaccinationController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/baby/vaccination")
@Api(tags = "宝宝疫苗接种相关接口")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @ApiOperation(value = "添加疫苗接种记录")
    @PostMapping(value = "/addVaccinationRecord")
    public ApiResult addVaccinationRecord(@Valid @RequestBody VaccinationDto vaccinationDto) {
        Long cid = RequestHolder.get().getCid();
        vaccinationService.addVaccinationRecord(cid, vaccinationDto);
        return ApiResult.success();
    }

    @ApiOperation(value = "查看疫苗接种记录")
    @PostMapping(value = "/getVaccinationRecord")
    public ApiResult<List<Vaccination>> getVaccinationRecord(@RequestBody PeriodTimeBaseReq periodTimeBaseReq) {
        Long cid = RequestHolder.get().getCid();
        return ApiResult.success(vaccinationService.getVaccinationRecord(cid, periodTimeBaseReq));
    }

    @ApiOperation(value = "修改疫苗接种记录")
    @PostMapping(value = "/modifyVaccinationRecord/{vaccinationId}")
    public ApiResult modifyVaccinationRecord(@Valid @RequestBody VaccinationDto vaccinationDto, @PathVariable Long vaccinationId) {
        Long cid = RequestHolder.get().getCid();
        return vaccinationService.modifyVaccinationRecord(cid, vaccinationDto, vaccinationId);
    }

    @ApiOperation(value = "删除疫苗接种记录")
    @DeleteMapping(value = "/deleteVaccinationRecord/{vaccinationId}")
    public ApiResult deleteVaccinationRecord(@PathVariable Long vaccinationId) {
        Long cid = RequestHolder.get().getCid();
        return vaccinationService.deleteVaccinationRecord(cid, vaccinationId);
    }
} 