package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.VaccinationDao;
import com.cqut.childcare.children.domain.dto.babyEvent.VaccinationDto;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.entity.Vaccination;
import com.cqut.childcare.children.service.babyEvent.VaccinationService;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.AppRuntimeException;
import com.cqut.childcare.common.exception.BabyEventEnum;
import com.cqut.childcare.customer.service.CustomerRelationService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationServiceImpl implements VaccinationService {
    @Autowired
    private VaccinationDao vaccinationDao;
    @Autowired
    private CustomerRelationService customerRelationService;
    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;

    @Override
    public void addVaccinationRecord(Long cid, VaccinationDto vaccinationDto) {
        CustomerBabyRelation relation = customerBabyRelationDao.getRelationByCidAndBabyId(cid, vaccinationDto.getBabyId());
        if (ObjectUtils.isEmpty(relation)) {
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        Vaccination vaccination = new Vaccination();
        BeanUtils.copyProperties(vaccinationDto, vaccination);
        vaccination.setCreateBy(cid);
        vaccinationDao.save(vaccination);
    }

    @Override
    public List<Vaccination> getVaccinationRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        return vaccinationDao.getVaccinationRecord(periodTimeBaseReq.getBabyId(), relationCustomers, periodTimeBaseReq);
    }

    @Override
    public ApiResult modifyVaccinationRecord(Long cid, VaccinationDto vaccinationDto, Long vaccinationId) {
        Vaccination record = vaccinationDao.getById(vaccinationId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            Vaccination temp = new Vaccination();
            BeanUtils.copyProperties(vaccinationDto, temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(vaccinationId);
            temp.setCreateBy(cid);
            vaccinationDao.updateById(temp);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteVaccinationRecord(Long cid, Long vaccinationId) {
        Vaccination record = vaccinationDao.getById(vaccinationId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            vaccinationDao.removeById(vaccinationId);
        }
        return ApiResult.success();
    }
} 