package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.Baby;
import com.cqut.childcare.children.mapper.BabyMapper;
import org.springframework.stereotype.Service;

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
}
