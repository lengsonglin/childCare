package com.cqut.childcare.children.service;

import com.cqut.childcare.children.domain.entity.PickUpInfo;

/**
 * @Description
 * @Author Faiz
 * @ClassName PickUpInfoService
 * @Version 1.0
 */
public interface PickUpInfoService {
    void addOrUpdate(PickUpInfo pickUpInfo);

    PickUpInfo getPickUpInfo(Long babyId);
}
