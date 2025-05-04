package com.cqut.childcare.children.service;

import com.cqut.childcare.children.domain.dto.AddBabyDto;
import com.cqut.childcare.children.domain.dto.CreateBabyDto;
import com.cqut.childcare.children.domain.entity.Baby;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyService
 * @Version 1.0
 */
public interface BabyService {
    void createBaby(CreateBabyDto babyCreateDto, Long cid);

    void addBaby(AddBabyDto addBabyDto, Long cid);

    void unbindBaby(Long cid, Long babyId);

    List<Baby> getRelatedBaby(Long cid);
}
