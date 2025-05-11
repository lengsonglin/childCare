package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.common.service.SystemSettingLoader;
import com.cqut.childcare.system.domain.entity.SystemSetting;
import com.cqut.childcare.system.mapper.SystemSettingMapper;
import com.cqut.childcare.system.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Faiz
 * @ClassName SystemSettingServiceImpl
 * @Version 1.0
 */
@Service
public class SystemSettingServiceImpl extends ServiceImpl<SystemSettingMapper, SystemSetting> implements SystemSettingService {

    @Autowired
    @Lazy
    private SystemSettingLoader systemSettingLoader;
    @Override
    public boolean updateSystemSetting(SystemSetting setting) {

        // 确保ID为1
        setting.setId(1L);
        //更新系统设置
        boolean flag = updateById(setting);
        systemSettingLoader.init();
        return flag;
    }
} 