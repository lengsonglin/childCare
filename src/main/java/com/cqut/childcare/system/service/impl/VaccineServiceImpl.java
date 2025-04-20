package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.system.domain.entity.Vaccine;
import com.cqut.childcare.system.mapper.VaccineMapper;
import com.cqut.childcare.system.service.VaccineService;
import org.springframework.stereotype.Service;

/**
 * @Description 疫苗信息Service实现类
 * @Author Faiz
 * @ClassName VaccineServiceImpl
 * @Version 1.0
 */
@Service
public class VaccineServiceImpl extends ServiceImpl<VaccineMapper, Vaccine> implements VaccineService {
    @Override
    public boolean addVaccine(Vaccine vaccine) {
        return save(vaccine);
    }

    @Override
    public boolean updateVaccine(Vaccine vaccine) {
        return updateById(vaccine);
    }

    @Override
    public boolean deleteVaccine(Long id) {
        return removeById(id);
    }
} 