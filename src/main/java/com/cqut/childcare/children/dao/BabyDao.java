package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.Baby;
import com.cqut.childcare.children.mapper.BabyMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyDao
 * @Version 1.0
 */
@Service
public class BabyDao extends ServiceImpl<BabyMapper, Baby> {
    public Baby getByBirthdayAndName(Date birthday, String name) {
        return lambdaQuery()
                .eq(Baby::getBirthday,birthday)
                .eq(Baby::getName,name)
                .one();

    }

    public void updateBabyInfo(Baby temp) {
        lambdaUpdate()
                .set(ObjectUtils.isNotEmpty(temp.getBirthday()),Baby::getBirthday,temp.getBirthday())
                .set(StringUtils.hasText(temp.getName()),Baby::getName,temp.getName())
                .set(StringUtils.hasText(temp.getIntroduce()),Baby::getIntroduce,temp.getIntroduce())
                .set(StringUtils.hasText(temp.getGender()),Baby::getGender,temp.getGender())
                .set(StringUtils.hasText(temp.getAvatar()),Baby::getAvatar,temp.getAvatar())
                .eq(Baby::getId,temp.getId())
                .update();
    }
}
