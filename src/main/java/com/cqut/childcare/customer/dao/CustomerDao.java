package com.cqut.childcare.customer.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.customer.domain.entity.Customer;
import com.cqut.childcare.customer.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

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
}
