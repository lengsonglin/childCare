package com.cqut.childcare.common.service;

import com.cqut.childcare.system.domain.entity.SystemSetting;
import com.cqut.childcare.system.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author Faiz
 * @ClassName SystemSettingLoader
 * @Version 1.0
 */
@Service
public class SystemSettingLoader {

    @Autowired
    private SystemSettingService systemSettingService;

    private static SystemSetting systemSetting;

    @PostConstruct
    public void init() {
        systemSetting= systemSettingService.getById(1L);
    }

    public static SystemSetting getSystemSetting(){
        return  systemSetting;
    }

}
