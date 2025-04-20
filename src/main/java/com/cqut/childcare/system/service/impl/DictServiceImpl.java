package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.system.domain.entity.DictKey;
import com.cqut.childcare.system.domain.entity.dictValue;
import com.cqut.childcare.system.mapper.DictKeyMapper;
import com.cqut.childcare.system.mapper.DictValueMapper;
import com.cqut.childcare.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 字典Service实现类
 * @Author Faiz
 * @ClassName DictServiceImpl
 * @Version 1.0
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictKeyMapper, DictKey> implements DictService {

    @Autowired
    private DictValueMapper dictValueMapper;

    @Override
    public boolean addDictKey(DictKey dictKey) {
        return save(dictKey);
    }

    @Override
    public boolean updateDictKey(DictKey dictKey) {
        return updateById(dictKey);
    }

    @Override
    public boolean deleteDictKey(Long id) {
        // 删除字典键时，同时删除关联的字典值
        dictValueMapper.deleteByDictKeyId(id);
        return removeById(id);
    }

    @Override
    public boolean addDictValue(dictValue dictValue) {
        return dictValueMapper.insert(dictValue) > 0;
    }

    @Override
    public boolean updateDictValue(dictValue dictValue) {
        return dictValueMapper.updateById(dictValue) > 0;
    }

    @Override
    public boolean deleteDictValue(Long id) {
        return dictValueMapper.deleteById(id) > 0;
    }

    @Override
    public List<dictValue> listDictValuesByKeyId(Long dictKeyId) {
        LambdaQueryWrapper<dictValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dictValue::getDictKeyId, dictKeyId);
        return dictValueMapper.selectList(queryWrapper);
    }
} 