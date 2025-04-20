package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.system.domain.entity.DictKey;
import com.cqut.childcare.system.domain.entity.dictValue;

import java.util.List;

/**
 * @Description 字典Service接口
 * @Author Faiz
 * @ClassName DictService
 * @Version 1.0
 */
public interface DictService extends IService<DictKey> {
    /**
     * 添加字典键
     * @param dictKey 字典键信息
     * @return 是否成功
     */
    boolean addDictKey(DictKey dictKey);

    /**
     * 更新字典键
     * @param dictKey 字典键信息
     * @return 是否成功
     */
    boolean updateDictKey(DictKey dictKey);

    /**
     * 删除字典键
     * @param id 字典键ID
     * @return 是否成功
     */
    boolean deleteDictKey(Long id);

    /**
     * 添加字典值
     * @param dictValue 字典值信息
     * @return 是否成功
     */
    boolean addDictValue(dictValue dictValue);

    /**
     * 更新字典值
     * @param dictValue 字典值信息
     * @return 是否成功
     */
    boolean updateDictValue(dictValue dictValue);

    /**
     * 删除字典值
     * @param id 字典值ID
     * @return 是否成功
     */
    boolean deleteDictValue(Long id);

    /**
     * 根据字典键ID获取字典值列表
     * @param dictKeyId 字典键ID
     * @return 字典值列表
     */
    List<dictValue> listDictValuesByKeyId(Long dictKeyId);
} 