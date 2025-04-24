package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.childcare.common.domain.vo.PageBaseResp;
import com.cqut.childcare.customer.domain.vo.CustomerInfoVo;
import com.cqut.childcare.system.domain.dto.ChildcareWorkerDto;
import org.springframework.http.ResponseEntity;

/**
 * @Description
 * @Author Faiz
 * @ClassName ChildcareWorkerService
 * @Version 1.0
 */
public interface ChildcareWorkerService {
    ResponseEntity<PageBaseResp<CustomerInfoVo>> getChildcareWorkerPage(Page plusPage, String name);

    int addChildcareWorker(ChildcareWorkerDto childcareWorkerDto);

    void deleteChildcareWorkerById(Long id);
}
