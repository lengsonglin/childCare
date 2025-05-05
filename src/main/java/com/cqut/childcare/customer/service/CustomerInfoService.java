package com.cqut.childcare.customer.service;


import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.customer.domain.dto.CustomerLoginAboutDto;
import com.cqut.childcare.customer.domain.dto.CustomerLoginDto;
import com.cqut.childcare.customer.domain.dto.ModifyCInfoDto;
import com.cqut.childcare.customer.domain.vo.CustomerBaseInfo;
import com.cqut.childcare.customer.domain.vo.CustomerInfoVo;
import com.cqut.childcare.customer.domain.vo.LoginVo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerInfoService
 * @Version 1.0
 */
public interface CustomerInfoService {
    void loginAbout(CustomerLoginAboutDto customerLoginAboutDto);

    ApiResult<LoginVo> login(CustomerLoginDto customerLoginDto);

    CustomerBaseInfo getCustomerInfo(Long cId);

    boolean verify(String token);

    Long getValidCid(String token);

    void renewalTokenIfNecessary(Long cid);

    CustomerInfoVo modifyCustomerInfo(Long cid, ModifyCInfoDto modifyCInfoDto);

    ResponseEntity<String> getFileUrl(String filePath);

    ApiResult getCustomerInfoByTelPhone(String telPhone);

    ResponseEntity<InputStreamResource> getFile(String filePath);
}
