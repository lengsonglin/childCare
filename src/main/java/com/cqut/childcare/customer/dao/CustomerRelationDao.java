package com.cqut.childcare.customer.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.customer.domain.entity.CustomerRelation;
import com.cqut.childcare.customer.mapper.CustomerRelationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerRelationDao
 * @Version 1.0
 */
@Service
public class CustomerRelationDao extends ServiceImpl<CustomerRelationMapper, CustomerRelation> {
    public List<CustomerRelation> getRelationByMainId(Long cid) {
        return lambdaQuery()
                .eq(CustomerRelation::getMainId,cid)
                .list();
    }

    public CustomerRelation getRelationBySubId(Long cid) {
        return lambdaQuery()
                .eq(CustomerRelation::getSubId,cid)
                .one();
    }
}
