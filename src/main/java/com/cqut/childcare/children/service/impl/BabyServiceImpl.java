package com.cqut.childcare.children.service.impl;

import com.cqut.childcare.children.dao.BabyDao;
import com.cqut.childcare.children.domain.dto.BabyAddDto;
import com.cqut.childcare.children.service.BabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyServiceImpl
 * @Version 1.0
 */
@Service
public class BabyServiceImpl implements BabyService {
    @Autowired
    private BabyDao babyDao;
    @Override
    public void createBaby(BabyAddDto babyCreateDto, Long cid) {

    }
}
