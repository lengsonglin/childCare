package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.system.domain.entity.DevelopmentCheck;
import com.cqut.childcare.system.mapper.DevelopmentCheckMapper;
import com.cqut.childcare.system.service.DevelopmentCheckService;
import org.springframework.stereotype.Service;

/**
 * @Description 发育检查Service实现类
 * @Author Faiz
 * @ClassName DevelopmentCheckServiceImpl
 * @Version 1.0
 */
@Service
public class DevelopmentCheckServiceImpl extends ServiceImpl<DevelopmentCheckMapper, DevelopmentCheck> implements DevelopmentCheckService {
    @Override
    public boolean addDevelopmentCheck(DevelopmentCheck developmentCheck) {
        return save(developmentCheck);
    }

    @Override
    public boolean updateDevelopmentCheck(DevelopmentCheck developmentCheck) {
        return updateById(developmentCheck);
    }

    @Override
    public boolean deleteDevelopmentCheck(Long id) {
        return removeById(id);
    }
} 