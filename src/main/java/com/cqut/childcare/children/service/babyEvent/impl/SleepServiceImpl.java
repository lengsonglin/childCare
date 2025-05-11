package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.SleepDao;
import com.cqut.childcare.children.domain.dto.babyEvent.SleepDto;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.entity.Sleep;
import com.cqut.childcare.children.service.babyEvent.SleepService;
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

/**
 * @Description 睡眠记录服务实现类
 * @Author Faiz
 * @ClassName SleepServiceImpl
 * @Version 1.0
 */
@Service
public class SleepServiceImpl implements SleepService {

    @Autowired
    private SleepDao sleepDao;

    @Autowired
    private CustomerRelationService customerRelationService;

    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;

    @Override
    public void addSleepRecord(Long cid, SleepDto sleepDto) {
        // 检查是否有亲属关系
        CustomerBabyRelation relation = customerBabyRelationDao.getRelationByCidAndBabyId(cid, sleepDto.getBabyId());
        if (ObjectUtils.isEmpty(relation)) {
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        Sleep sleep = new Sleep();
        BeanUtils.copyProperties(sleepDto, sleep);
        sleep.setCreateBy(cid);
        sleepDao.save(sleep);
    }

    @Override
    public List<Sleep> getSleepRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        return sleepDao.getSleepRecord(periodTimeBaseReq.getBabyId(), relationCustomers, periodTimeBaseReq);
    }

    @Override
    public ApiResult modifySleepRecord(Long cid, SleepDto sleepDto, Long sleepId) {
        Sleep record = sleepDao.getById(sleepId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            Sleep temp = new Sleep();
            BeanUtils.copyProperties(sleepDto, temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(sleepId);
            sleepDao.updateById(temp);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteSleepRecord(Long cid, Long sleepId) {
        Sleep record = sleepDao.getById(sleepId);
        if (ObjectUtils.isNotEmpty(record)) {
            if (!record.getCreateBy().equals(cid)) {
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            sleepDao.removeById(sleepId);
        }
        return ApiResult.success();
    }

    @Override
    public Sleep getSleepRecordOne(Long recordId) {
        return sleepDao.getById(recordId);
    }
} 