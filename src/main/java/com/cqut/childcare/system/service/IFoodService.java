package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.system.domain.entity.Food;

import java.util.List;

public interface IFoodService extends IService<Food> {
    /**
     * 保存食物
     */
    boolean save(Food food);

    /**
     * 根据ID删除食物
     */
    boolean removeById(Long id);

    /**
     * 更新食物
     */
    boolean updateById(Food food);

    /**
     * 根据ID获取食物
     */
    Food getById(Long id);

    /**
     * 获取所有食物列表
     */
    List<Food> list();
} 