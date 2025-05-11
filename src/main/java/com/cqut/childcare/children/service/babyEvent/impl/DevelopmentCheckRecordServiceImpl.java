package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.DevelopmentCheckRecordDao;
import com.cqut.childcare.children.domain.dto.babyEvent.DevelopmentCheckRecordDto;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.entity.DevelopmentCheckRecord;
import com.cqut.childcare.children.service.babyEvent.DevelopmentCheckRecordService;
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
public class DevelopmentCheckRecordServiceImpl implements DevelopmentCheckRecordService {
    @Autowired
    private DevelopmentCheckRecordDao developmentCheckRecordDao;
    @Autowired
    private CustomerRelationService customerRelationService;
    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;

    @Override
    public void addDevelopmentCheckRecord(Long cid, DevelopmentCheckRecordDto dto) {
        CustomerBabyRelation relation = customerBabyRelationDao.getRelationByCidAndBabyId(cid, dto.getBabyId());
        if (ObjectUtils.isEmpty(relation)) {
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        DevelopmentCheckRecord record = new DevelopmentCheckRecord();
        BeanUtils.copyProperties(dto, record);
        record.setCreateBy(cid);
        developmentCheckRecordDao.save(record);
    }

    @Override
    public List<DevelopmentCheckRecord> getDevelopmentCheckRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        return developmentCheckRecordDao.getDevelopmentCheckRecord(periodTimeBaseReq.getBabyId(), relationCustomers, periodTimeBaseReq);
    }

    @Override
    public ApiResult modifyDevelopmentCheckRecord(Long cid, DevelopmentCheckRecordDto dto, Long recordId) {
        DevelopmentCheckRecord record = developmentCheckRecordDao.getById(recordId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            DevelopmentCheckRecord temp = new DevelopmentCheckRecord();
            BeanUtils.copyProperties(dto, temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(recordId);
            developmentCheckRecordDao.updateById(temp);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteDevelopmentCheckRecord(Long cid, Long recordId) {
        DevelopmentCheckRecord record = developmentCheckRecordDao.getById(recordId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            developmentCheckRecordDao.removeById(recordId);
        }
        return ApiResult.success();
    }

    @Override
    public DevelopmentCheckRecord getDevelopmentCheckRecordOne( Long recordId) {
        return developmentCheckRecordDao.getDevelopmentCheckRecordOne(recordId);
    }
} 