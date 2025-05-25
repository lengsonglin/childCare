package com.cqut.childcare.customer.service;

import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.customer.domain.dto.AddFamilyDto;
import com.cqut.childcare.customer.domain.vo.CustomerBaseInfo;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerRelationService
 * @Version 1.0
 */
public interface CustomerRelationService {
    ApiResult addFamily(Long cid, AddFamilyDto addFamilyDto);

    List<Long> getRelationCustomer(Long cid,Long babyId);

    List<CustomerBaseInfo> getFamily(Long cid);
}
