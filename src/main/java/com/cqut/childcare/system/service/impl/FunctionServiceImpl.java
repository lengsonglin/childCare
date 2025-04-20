package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.system.domain.entity.Function;
import com.cqut.childcare.system.mapper.FunctionMapper;
import com.cqut.childcare.system.service.FunctionService;
import org.springframework.stereotype.Service;

/**
 * @Description 功能Service实现类
 * @Author Faiz
 * @ClassName FunctionServiceImpl
 * @Version 1.0
 */
@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService {
    @Override
    public boolean addFunction(Function function) {
        return save(function);
    }

    @Override
    public boolean updateFunction(Function function) {
        return updateById(function);
    }

    @Override
    public boolean deleteFunction(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateFunctionState(Long id, Byte onState) {
        Function function = new Function();
        function.setId(id);
        function.setOnState(onState);
        return updateById(function);
    }
} 