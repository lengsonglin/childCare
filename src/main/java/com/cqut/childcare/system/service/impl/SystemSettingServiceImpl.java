package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.system.domain.entity.systemSetting;
import com.cqut.childcare.system.mapper.SystemSettingMapper;
import com.cqut.childcare.system.service.SystemSettingService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Faiz
 * @ClassName SystemSettingServiceImpl
 * @Version 1.0
 */
@Service
public class SystemSettingServiceImpl extends ServiceImpl<SystemSettingMapper, systemSetting> implements SystemSettingService {
    @Override
    public boolean updateSystemSetting(systemSetting setting) {
        // 确保ID为1
        setting.setId(1L);
        return updateById(setting);
    }
} 