package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.Vaccination;
import com.cqut.childcare.children.mapper.VaccinationMapper;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Faiz
 * @ClassName VaccinationDao
 * @Version 1.0
 */
@Service
public class VaccinationDao extends ServiceImpl<VaccinationMapper, Vaccination> {
} 