package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.system.domain.entity.systemSetting;

/**
 * @Description
 * @Author Faiz
 * @ClassName SystemSettingService
 * @Version 1.0
 */
public interface SystemSettingService extends IService<systemSetting> {
    /**
     * 更新系统设置
     * @param setting 系统设置信息
     * @return 是否更新成功
     */
    boolean updateSystemSetting(systemSetting setting);
} 