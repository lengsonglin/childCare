package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CleanDao;
import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.domain.dto.babyEvent.CleanDto;
import com.cqut.childcare.children.domain.entity.Clean;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.service.babyEvent.CleanService;
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
 * @Description
 * @Author Faiz
 * @ClassName CleanServiceImpl
 * @Version 1.0
 */
@Service
public class CleanServiceImpl implements CleanService {

    @Autowired
    private CleanDao cleanDao;

    @Autowired
    private CustomerRelationService customerRelationService;

    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;
    @Override
    public void addCleanRecord(Long cid, CleanDto cleanDto) {
        //检查是否有亲属关系
        CustomerBabyRelation relation = customerBabyRelationDao.getRelationByCidAndBabyId(cid, cleanDto.getBabyId());
        if(ObjectUtils.isEmpty(relation)){
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        Clean clean = new Clean();
        BeanUtils.copyProperties(cleanDto,clean);
        clean.setCreateBy(cid);
        cleanDao.save(clean);
    }

    @Override
    public List<Clean> getCleanRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        List<Clean> data = cleanDao.getCleanRecord(periodTimeBaseReq.getBabyId(),relationCustomers,periodTimeBaseReq);
        return data;
    }

    @Override
    public ApiResult modifyCleanRecord(Long cid,CleanDto cleanDto, Long cleanId) {
        //查看当前记录是否是当前用户添加
        Clean record = cleanDao.getById(cleanId);
        if(ObjectUtils.isNotEmpty(record)){
            if(!record.getCreateBy().equals(cid)){
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            Clean temp = new Clean();
            BeanUtils.copyProperties(cleanDto,temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(cleanId);
            cleanDao.updateById(temp);
        }

        return ApiResult.success();
    }

    @Override
    public ApiResult deleteCleanRecord(Long cid, Long cleanId) {
        //查看当前记录是否是当前用户添加
        Clean record = cleanDao.getById(cleanId);
        if(ObjectUtils.isNotEmpty(record)){
            if(!record.getCreateBy().equals(cid)){
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            cleanDao.removeById(cleanId);
        }
        return ApiResult.success();
    }
}
