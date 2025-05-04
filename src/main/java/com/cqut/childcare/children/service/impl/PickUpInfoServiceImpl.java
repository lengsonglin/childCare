package com.cqut.childcare.children.service.impl;

import com.cqut.childcare.children.dao.PickUpInfoDao;
import com.cqut.childcare.children.domain.entity.PickUpInfo;
import com.cqut.childcare.children.service.PickUpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public void addOrUpdate(PickUpInfo pickUpInfo) {
        pickUpInfoDao.saveOrUpdate(pickUpInfo);
    }

    @Override
    public PickUpInfo getPickUpInfo(Long babyId) {
        return pickUpInfoDao.getPickUpInfo(babyId);
    }
}
