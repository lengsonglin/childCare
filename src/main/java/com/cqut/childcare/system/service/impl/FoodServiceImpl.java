package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.system.domain.entity.Food;
import com.cqut.childcare.system.mapper.FoodMapper;
import com.cqut.childcare.system.service.IFoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {

    @Override
    public boolean save(Food food) {
        return super.save(food);
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Food food) {
        return super.updateById(food);
    }

    @Override
    public Food getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<Food> list() {
        return super.list();
    }
} 