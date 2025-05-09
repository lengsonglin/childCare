package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.Temperature;
import com.cqut.childcare.children.mapper.TemperatureMapper;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Faiz
 * @ClassName TemperatureDao
 * @Version 1.0
 */
@Service
public class TemperatureDao extends ServiceImpl<TemperatureMapper, Temperature> {
} 