package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.customer.dao.CustomerDao;
import com.cqut.childcare.customer.domain.entity.Customer;
import com.cqut.childcare.customer.domain.enums.CustomerTypeEnum;
import com.cqut.childcare.customer.domain.vo.CustomerInfoVo;
import com.cqut.childcare.system.domain.dto.ChildcareWorkerDto;
import com.cqut.childcare.system.service.ChildcareWorkerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Faiz
 * @ClassName ChildcareWorkerServiceImpl
 * @Version 1.0
 */
@Service
public class ChildcareWorkerServiceImpl implements ChildcareWorkerService {

    @Autowired
    private CustomerDao customerDao;
    @Override
    public ResponseEntity<PageBaseResp<CustomerInfoVo>> getChildcareWorkerPage(Page page, String name) {
        List<Customer> list= customerDao.getChildcareWorkerPage(name);
        List<CustomerInfoVo> data = list.stream()
                .map(customer -> {
                    CustomerInfoVo vo = new CustomerInfoVo();
                    BeanUtils.copyProperties(customer, vo); // 复制属性
                    return vo;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(PageBaseResp.init(page,data));
    }

    @Override
    public int addChildcareWorker(ChildcareWorkerDto childcareWorkerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(childcareWorkerDto,customer);
        customer.setPassword(DigestUtils.md5DigestAsHex(childcareWorkerDto.getPassword().getBytes()));
        customer.setType(CustomerTypeEnum.CHILDCARE_WORKER.getType());
        customer.setRegisterTime(new Date());
        return customerDao.addChildcareWorker(customer);
    }

    @Override
    public void deleteChildcareWorkerById(Long id) {
        customerDao.deleteById(id);
    }
}
