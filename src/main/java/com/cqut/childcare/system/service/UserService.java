package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.system.domain.dto.UserInfoDto;
import com.cqut.childcare.system.domain.entity.User;

/**
 * @Description
 * @Author Faiz
 * @ClassName UserService
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    /**
     *  修改管理员信息
     * @param userInfoDto
     * @return
     */
    boolean updateUserInfo(UserInfoDto userInfoDto);
} 