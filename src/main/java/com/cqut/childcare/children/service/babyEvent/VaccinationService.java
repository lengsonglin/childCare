package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.VaccinationDto;
import com.cqut.childcare.children.domain.entity.Vaccination;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description 疫苗接种记录服务接口
 * @Author Faiz
 * @ClassName VaccinationService
 * @Version 1.0
 */
public interface VaccinationService {
    void addVaccinationRecord(Long cid, VaccinationDto vaccinationDto);

    List<Vaccination> getVaccinationRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifyVaccinationRecord(Long cid, VaccinationDto vaccinationDto, Long vaccinationId);

    ApiResult deleteVaccinationRecord(Long cid, Long vaccinationId);
} 