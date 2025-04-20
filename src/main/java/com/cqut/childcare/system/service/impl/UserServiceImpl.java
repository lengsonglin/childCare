package com.cqut.childcare.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.system.domain.dto.UserInfoDto;
import com.cqut.childcare.system.domain.entity.User;
import com.cqut.childcare.system.mapper.UserMapper;
import com.cqut.childcare.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Faiz
 * @ClassName UserServiceImpl
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public boolean updateUserInfo(UserInfoDto userInfoDto) {
        return lambdaUpdate()
                .eq(User::getId,userInfoDto.getId())
                .set(StringUtils.isNotBlank(userInfoDto.getAccount()),User::getAccount,userInfoDto.getAccount())
                .set(StringUtils.isNotBlank(userInfoDto.getName()),User::getName,userInfoDto.getName())
                .set(StringUtils.isNotBlank(userInfoDto.getEmail()),User::getEmail,userInfoDto.getEmail())
                .set(StringUtils.isNotBlank(userInfoDto.getTelPhone()),User::getTelPhone,userInfoDto.getTelPhone())
                .set(StringUtils.isNotBlank(userInfoDto.getRemark()),User::getRemark,userInfoDto.getRemark())
                .update();

    }
}