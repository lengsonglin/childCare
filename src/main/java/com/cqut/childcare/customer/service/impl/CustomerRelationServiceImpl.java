package com.cqut.childcare.customer.service.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.enums.RelationshipTypeEnum;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.AppRuntimeException;
import com.cqut.childcare.common.exception.BabyEventEnum;
import com.cqut.childcare.common.exception.RelationErrorEnum;
import com.cqut.childcare.customer.dao.CustomerRelationDao;
import com.cqut.childcare.customer.domain.entity.CustomerRelation;
import com.cqut.childcare.customer.service.CustomerRelationService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerRelationServiceImpl
 * @Version 1.0
 */
@Service
public class CustomerRelationServiceImpl implements CustomerRelationService {
    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;

    @Autowired
    private CustomerRelationDao customerRelationDao;


    @Override
    public ApiResult addFamily(Long cid, Long otherFamilyCid) {
        List<CustomerBabyRelation>  relations1= customerBabyRelationDao.getRelationByCid(cid);
        List<CustomerBabyRelation>  relations2= customerBabyRelationDao.getRelationByCid(otherFamilyCid);

        if(ObjectUtils.isNotEmpty(relations1) && ObjectUtils.isNotEmpty(relations2)){
            //可能一个家庭有几个孩子     有一个宝宝相同，就可以确定是一家人了
            for (CustomerBabyRelation relation1: relations1) {
                for (CustomerBabyRelation relation2: relations2) {
                    if(relation1.getBabyId() == relation2.getBabyId() &&
                            relation1.getRelationship()== RelationshipTypeEnum.MAIN_FAMILY.getType()){
                        CustomerRelation build = CustomerRelation.builder().mainId(cid).subId(otherFamilyCid).build();
                        customerRelationDao.save(build);
                        return ApiResult.success();
                    }
                }
            }
        }
        return ApiResult.fail(RelationErrorEnum.ADD_FAMILY_ERROR);
    }

    /**
     * 查询关联了的家长，包含自己
     * @param cid
     * @param babyId
     * @return
     */
    @Override
    public List<Long> getRelationCustomer(Long cid,Long babyId) {
        //查看是主家长 还是其他家长
        CustomerBabyRelation customerBabyRelation  = customerBabyRelationDao.getRelationByCidAndBabyId(cid,babyId);
        if(ObjectUtils.isEmpty(customerBabyRelation)){
            throw new AppRuntimeException(BabyEventEnum.PERMISSION_ERROR);
        }
        List<Long> relations = new ArrayList<>();
        if (customerBabyRelation.getRelationship()== RelationshipTypeEnum.MAIN_FAMILY.getType()) {
            List<CustomerRelation> customerRelations = customerRelationDao.getRelationByMainId(cid);
            relations = customerRelations.stream().map(CustomerRelation::getSubId).collect(Collectors.toList());
        }else {
            //如果是其他家长，看是否与主家长关联
            CustomerRelation customerRelation = customerRelationDao.getRelationBySubId(cid);
            //关联的话 就可以查看所有记录
            if(ObjectUtils.isNotEmpty(customerRelation)){
                List<CustomerRelation> customerRelations = customerRelationDao.getRelationByMainId(customerRelation.getMainId());
                relations = customerRelations.stream().map(CustomerRelation::getSubId).collect(Collectors.toList());
                //托育员添加的记录
                List<CustomerBabyRelation> relation = customerBabyRelationDao.getRelationByType(RelationshipTypeEnum.CHILDCARE_WORKER.getType(),babyId);
                for (CustomerBabyRelation item:relation) {
                    relations.add(item.getCustomerId());
                }
            }

        }
        relations.add(cid);
        return relations;
    }


}
