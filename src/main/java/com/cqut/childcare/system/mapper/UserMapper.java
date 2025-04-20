package com.cqut.childcare.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.childcare.system.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author Faiz
 * @ClassName UserMapper
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
} 