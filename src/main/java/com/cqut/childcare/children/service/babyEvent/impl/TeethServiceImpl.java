package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.TeethDao;
import com.cqut.childcare.children.domain.dto.babyEvent.TeethDto;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.entity.Teeth;
import com.cqut.childcare.children.service.babyEvent.TeethService;
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
public class TeethServiceImpl implements TeethService {
    @Autowired
    private TeethDao teethDao;
    @Autowired
    private CustomerRelationService customerRelationService;
    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;

    @Override
    public void addTeethRecord(Long cid, TeethDto teethDto) {
        CustomerBabyRelation relation = customerBabyRelationDao.getRelationByCidAndBabyId(cid, teethDto.getBabyId());
        if (ObjectUtils.isEmpty(relation)) {
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        Teeth teeth = new Teeth();
        BeanUtils.copyProperties(teethDto, teeth);
        teeth.setCreateBy(cid);
        teethDao.save(teeth);
    }

    @Override
    public List<Teeth> getTeethRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        return teethDao.getTeethRecord(periodTimeBaseReq.getBabyId(), relationCustomers, periodTimeBaseReq);
    }

    @Override
    public ApiResult modifyTeethRecord(Long cid, TeethDto teethDto, Long teethId) {
        Teeth record = teethDao.getById(teethId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            Teeth temp = new Teeth();
            BeanUtils.copyProperties(teethDto, temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(teethId);
            teethDao.updateById(temp);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteTeethRecord(Long cid, Long teethId) {
        Teeth record = teethDao.getById(teethId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            teethDao.removeById(teethId);
        }
        return ApiResult.success();
    }

    @Override
    public Teeth getTeethRecordOne(Long teethId) {
        return teethDao.getTeethRecordOne(teethId);
    }
} 