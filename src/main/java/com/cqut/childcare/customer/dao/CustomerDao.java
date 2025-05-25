package com.cqut.childcare.customer.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.customer.domain.dto.AddFamilyDto;
import com.cqut.childcare.customer.domain.entity.Customer;
import com.cqut.childcare.customer.domain.enums.CustomerTypeEnum;
import com.cqut.childcare.customer.mapper.CustomerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerDao
 * @Version 1.0
 */
@Service
public class CustomerDao extends ServiceImpl<CustomerMapper, Customer> {
    public Customer getByTelPhone(String telPhone) {
        return lambdaQuery()
                .eq(Customer::getTelPhone,telPhone)
                .one();
    }

    public Customer getByCid(Long cid){
        return  lambdaQuery()
                .eq(Customer::getId,cid)
                .one();
    }

    public void modifyCustomerInfo(Customer customer) {
        updateById(customer);
    }

    public boolean modifyPasswordByTelphone(String password, String telPhone) {
        return lambdaUpdate()
                .eq(Customer::getTelPhone,telPhone)
                .set(Customer::getPassword,password)
                .update();
    }

    public List<Customer> getChildcareWorkerPage(String name) {
        return lambdaQuery()
                .likeRight(StringUtils.isNotBlank(name),Customer::getRealName,name)
                .eq(Customer::getType, CustomerTypeEnum.CHILDCARE_WORKER.getType())
                .list();

    }

    public int addChildcareWorker(Customer customer) {
        return lambdaUpdate().getBaseMapper().insert(customer);
    }

    public void deleteById(Long id) {
        lambdaUpdate().eq(Customer::getId,id)
                .remove();
    }

    public Customer getCustomerInfoByTelPhone(String telPhone) {
        return lambdaQuery().eq(StringUtils.isNotBlank(telPhone),Customer::getTelPhone,telPhone)
                .one();
    }

    public List<Customer> getByCids(List<Long> cids) {
        return lambdaQuery()
                .in(Customer::getId,cids)
                .list();
    }

    public Customer getByNameAndTelPhone(AddFamilyDto addFamilyDto) {
        return lambdaQuery()
                .eq(Customer::getTelPhone,addFamilyDto.getTelPhone())
                .eq(Customer::getRealName,addFamilyDto.getRealName())
                .one();
    }
}
