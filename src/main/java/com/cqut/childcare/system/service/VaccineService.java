package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.system.domain.entity.Vaccine;

/**
 * @Description 疫苗信息Service接口
 * @Author Faiz
 * @ClassName VaccineService
 * @Version 1.0
 */
public interface VaccineService extends IService<Vaccine> {
    /**
     * 添加疫苗信息
     * @param vaccine 疫苗信息
     * @return 是否成功
     */
    boolean addVaccine(Vaccine vaccine);

    /**
     * 更新疫苗信息
     * @param vaccine 疫苗信息
     * @return 是否成功
     */
    boolean updateVaccine(Vaccine vaccine);

    /**
     * 删除疫苗信息
     * @param id 疫苗ID
     * @return 是否成功
     */
    boolean deleteVaccine(Long id);
} 