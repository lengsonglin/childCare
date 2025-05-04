package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.mapper.CustomerBabyRelationMapper;
import com.cqut.childcare.customer.domain.entity.CustomerRelation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerBabyRelationDao
 * @Version 1.0
 */
@Service
public class CustomerBabyRelationDao extends ServiceImpl<CustomerBabyRelationMapper, CustomerBabyRelation> {
    public List<CustomerBabyRelation> getRelationByCid(Long cid) {
        return lambdaQuery().eq(CustomerBabyRelation::getCustomerId,cid)
                .list();
    }

    public CustomerBabyRelation getRelationByCidAndBabyId(Long cid, Long babyId) {
        return lambdaQuery()
                .eq(CustomerBabyRelation::getBabyId,babyId)
                .eq(CustomerBabyRelation::getCustomerId,cid)
                .one();
    }

    public List<CustomerBabyRelation> getRelationByType(Integer type,Long babyId) {
        return lambdaQuery()
                .eq(CustomerBabyRelation::getBabyId,babyId)
                .eq(CustomerBabyRelation::getRelationship,type)
                .list();
    }

    public void unbindRelation(Long cid, Long babyId) {
        lambdaUpdate()
                .eq(CustomerBabyRelation::getCustomerId,cid)
                .eq(CustomerBabyRelation::getBabyId,babyId)
                .remove();
    }
}
