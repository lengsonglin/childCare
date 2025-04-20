package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.system.domain.entity.DevelopmentCheck;

/**
 * @Description 发育检查Service接口
 * @Author Faiz
 * @ClassName DevelopmentCheckService
 * @Version 1.0
 */
public interface DevelopmentCheckService extends IService<DevelopmentCheck> {
    /**
     * 添加发育检查记录
     * @param developmentCheck 发育检查信息
     * @return 是否成功
     */
    boolean addDevelopmentCheck(DevelopmentCheck developmentCheck);

    /**
     * 更新发育检查记录
     * @param developmentCheck 发育检查信息
     * @return 是否成功
     */
    boolean updateDevelopmentCheck(DevelopmentCheck developmentCheck);

    /**
     * 删除发育检查记录
     * @param id 发育检查ID
     * @return 是否成功
     */
    boolean deleteDevelopmentCheck(Long id);
} 