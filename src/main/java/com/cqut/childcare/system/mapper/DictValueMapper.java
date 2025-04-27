package com.cqut.childcare.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.childcare.system.domain.entity.dictValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author Faiz
 * @ClassName DictValueMapper
 * @Version 1.0
 */

public interface DictValueMapper extends BaseMapper<dictValue> {
    /**
     * 根据字典键ID删除字典值
     * @param dictKeyId 字典键ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM t_dict_value WHERE dict_key_id = #{dictKeyId}")
    int deleteByDictKeyId(Long dictKeyId);
} 