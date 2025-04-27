package com.cqut.childcare.children.service.impl;

import com.cqut.childcare.children.dao.BabyDao;
import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.domain.dto.BabyAddDto;
import com.cqut.childcare.children.domain.entity.Baby;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.enums.RelationshipTypeEnum;
import com.cqut.childcare.children.service.BabyService;
import com.cqut.childcare.common.constant.MinioBucketConstant;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.minIo.service.OssService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;
    @Autowired
    private OssService ossService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBaby(BabyAddDto babyCreateDto, Long cid) {
        //1. 先检查这个宝宝是否已经存在
        Date birthday = babyCreateDto.getBirthday();
        String name = babyCreateDto.getName();
        Baby baby = babyDao.getByBirthdayAndName(birthday,name);
        Long babyId;
        CustomerBabyRelation relationship = new CustomerBabyRelation();
        // 2. 根据宝宝是否存在确定用户身份
        if (ObjectUtils.isEmpty(baby)) {
            MultipartFile file = babyCreateDto.getAvatarFile();
            Baby temp = new Baby();
            BeanUtils.copyProperties(babyCreateDto,temp);
            temp.setCreateTime(new Date());
            if(ObjectUtils.isNotEmpty(file)) {
                String s = ossService.uploadAvatar(file, MinioBucketConstant.BABY_AVATAR_BUCKET);
                temp.setAvatar(s);
            }
            babyDao.save(temp);
            babyId = temp.getId();
            relationship.setRelationship(RelationshipTypeEnum.MAIN_FAMILY.getType());
        }else{
            babyId = baby.getId();
            //判断是否已经绑定
            List<CustomerBabyRelation> relations = customerBabyRelationDao.getRelationByCid(cid);
            for (CustomerBabyRelation item:relations) {
                if(item.getBabyId() == babyId)
                    return;
            }
            relationship.setRelationship(RelationshipTypeEnum.SUB_FAMILY.getType());
        }
        relationship.setBabyId(babyId);
        relationship.setCustomerId(cid);
        customerBabyRelationDao.save(relationship);
    }
}
