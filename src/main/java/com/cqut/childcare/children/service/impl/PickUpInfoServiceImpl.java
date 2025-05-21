package com.cqut.childcare.children.service.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.PickUpInfoDao;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.entity.PickUpInfo;
import com.cqut.childcare.children.service.PickUpInfoService;
import com.cqut.childcare.common.exception.AppRuntimeException;
import com.cqut.childcare.common.exception.BabyEventEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author Faiz
 * @ClassName PickUpInfoServiceImpl
 * @Version 1.0
 */
@Service
public class PickUpInfoServiceImpl implements PickUpInfoService {
    @Autowired
    private PickUpInfoDao pickUpInfoDao;

    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;
    @Override
    public void addOrUpdate(Long cid,PickUpInfo pickUpInfo) {
        List<CustomerBabyRelation> relations = customerBabyRelationDao.getRelationByCid(cid);
        for (CustomerBabyRelation relation: relations) {
            if(Objects.equals(relation.getBabyId(), pickUpInfo.getBabyId())){
                if(relation.getRelationship() == 0){
                    pickUpInfoDao.saveOrUpdate(pickUpInfo);
                    return;
                }
            }
        }
        throw new AppRuntimeException(BabyEventEnum.PERMISSION_OUT);
    }

    @Override
    public PickUpInfo getPickUpInfo(Long babyId) {
        return pickUpInfoDao.getPickUpInfo(babyId);
    }
}
