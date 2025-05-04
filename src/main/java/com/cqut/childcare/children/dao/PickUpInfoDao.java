package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.PickUpInfo;
import com.cqut.childcare.children.mapper.PickUpInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Faiz
 * @ClassName PickUpInfoDao
 * @Version 1.0
 */
@Service
public class PickUpInfoDao extends ServiceImpl<PickUpInfoMapper, PickUpInfo> {
    public PickUpInfo getPickUpInfo(Long babyId) {
        return lambdaQuery()
                .eq(PickUpInfo::getBabyId,babyId)
                .one();
    }
}
