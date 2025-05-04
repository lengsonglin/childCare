package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.GrowthRecordDao;
import com.cqut.childcare.children.domain.dto.babyEvent.GrowthRecordDto;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.entity.GrowthRecord;
import com.cqut.childcare.children.service.babyEvent.GrowthRecordService;
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
public class GrowthRecordServiceImpl implements GrowthRecordService {
    @Autowired
    private GrowthRecordDao growthRecordDao;
    @Autowired
    private CustomerRelationService customerRelationService;
    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;

    @Override
    public void addGrowthRecord(Long cid, GrowthRecordDto growthRecordDto) {
        CustomerBabyRelation relation = customerBabyRelationDao.getRelationByCidAndBabyId(cid, growthRecordDto.getBabyId());
        if (ObjectUtils.isEmpty(relation)) {
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        GrowthRecord growthRecord = new GrowthRecord();
        BeanUtils.copyProperties(growthRecordDto, growthRecord);
        growthRecord.setCreateBy(cid);
        growthRecordDao.save(growthRecord);
    }

    @Override
    public List<GrowthRecord> getGrowthRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        return growthRecordDao.getGrowthRecord(periodTimeBaseReq.getBabyId(), relationCustomers, periodTimeBaseReq);
    }

    @Override
    public ApiResult modifyGrowthRecord(Long cid, GrowthRecordDto growthRecordDto, Long recordId) {
        GrowthRecord record = growthRecordDao.getById(recordId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            GrowthRecord temp = new GrowthRecord();
            BeanUtils.copyProperties(growthRecordDto, temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(recordId);
            growthRecordDao.updateById(temp);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteGrowthRecord(Long cid, Long recordId) {
        GrowthRecord record = growthRecordDao.getById(recordId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            growthRecordDao.removeById(recordId);
        }
        return ApiResult.success();
    }
} 