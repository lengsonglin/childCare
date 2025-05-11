package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.SickDao;
import com.cqut.childcare.children.domain.dto.babyEvent.SickDto;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.entity.Sick;
import com.cqut.childcare.children.service.babyEvent.SickService;
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
 * @Description 生病记录服务实现类
 * @Author Faiz
 * @ClassName SickServiceImpl
 * @Version 1.0
 */
@Service
public class SickServiceImpl implements SickService {

    @Autowired
    private SickDao sickDao;

    @Autowired
    private CustomerRelationService customerRelationService;

    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;

    @Override
    public void addSickRecord(Long cid, SickDto sickDto) {
        //检查是否有亲属关系
        CustomerBabyRelation relation = customerBabyRelationDao.getRelationByCidAndBabyId(cid, sickDto.getBabyId());
        if(ObjectUtils.isEmpty(relation)){
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        Sick sick = new Sick();
        BeanUtils.copyProperties(sickDto, sick);
        sick.setCreateBy(cid);
        sickDao.save(sick);
    }

    @Override
    public List<Sick> getSickRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        return sickDao.getSickRecord(periodTimeBaseReq.getBabyId(), relationCustomers, periodTimeBaseReq);
    }

    @Override
    public ApiResult modifySickRecord(Long cid, SickDto sickDto, Long sickId) {
        //查看当前记录是否是当前用户添加
        Sick record = sickDao.getById(sickId);
        if(ObjectUtils.isNotEmpty(record)){
            if(!record.getCreateBy().equals(cid)){
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            Sick temp = new Sick();
            BeanUtils.copyProperties(sickDto, temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(sickId);
            sickDao.updateById(temp);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteSickRecord(Long cid, Long sickId) {
        //查看当前记录是否是当前用户添加
        Sick record = sickDao.getById(sickId);
        if(ObjectUtils.isNotEmpty(record)){
            if(!record.getCreateBy().equals(cid)){
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            sickDao.removeById(sickId);
        }
        return ApiResult.success();
    }

    @Override
    public Sick getSickRecordOne(Long recordId) {
        return sickDao.getById(recordId);
    }
} 