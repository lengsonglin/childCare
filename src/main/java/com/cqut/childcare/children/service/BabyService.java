package com.cqut.childcare.children.service;

import com.cqut.childcare.children.domain.dto.BabyAddDto;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyService
 * @Version 1.0
 */
public interface BabyService {
    void createBaby(BabyAddDto babyCreateDto, Long cid);
}
