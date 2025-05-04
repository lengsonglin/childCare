package com.cqut.childcare.children.service.impl;

import com.cqut.childcare.children.dao.BabyDao;
import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.domain.dto.AddBabyDto;
import com.cqut.childcare.children.domain.dto.CreateBabyDto;
import com.cqut.childcare.children.domain.entity.Baby;
import com.cqut.childcare.children.domain.entity.CustomerBabyRelation;
import com.cqut.childcare.children.domain.enums.RelationshipTypeEnum;
import com.cqut.childcare.children.domain.vo.BabyVo;
import com.cqut.childcare.children.mapper.BabyMapper;
import com.cqut.childcare.children.service.BabyService;
import com.cqut.childcare.common.constant.MinioBucketConstant;
import com.cqut.childcare.common.exception.AppRuntimeException;
import com.cqut.childcare.common.exception.BabyEventEnum;
import com.cqut.childcare.minIo.service.OssService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private BabyMapper babyMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBaby(CreateBabyDto babyCreateDto, Long cid) {
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
                String s = ossService.uploadFile(file, MinioBucketConstant.BABY_AVATAR_BUCKET);
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

    @Override
    public void addBaby(AddBabyDto addBabyDto, Long cid) {
        //1. 先检查这个宝宝是否已经存在
        Date birthday = addBabyDto.getBirthday();
        String name = addBabyDto.getName();
        Baby baby = babyDao.getByBirthdayAndName(birthday,name);
        if(ObjectUtils.isEmpty(baby)){
            throw new AppRuntimeException(BabyEventEnum.BABY_NOT_EXIST);
        }
        customerBabyRelationDao.save(CustomerBabyRelation.builder()
                .babyId(baby.getId())
                .customerId(cid)
                .build());

    }

    @Override
    public void unbindBaby(Long cid, Long babyId) {
        customerBabyRelationDao.unbindRelation(cid,babyId);
    }

    @Override
    public List<BabyVo> getRelatedBaby(Long cid) {
        List<Baby> babyList = babyMapper.getRelatedBaby(cid);
        List<BabyVo> babyVoList = babyList.stream()
                .map(baby -> BabyVo.builder()
                        .id(baby.getId())
                        .name(baby.getName())
                        .gender(baby.getGender())
                        .age(calculateAge(baby.getBirthday())) // 调用年龄计算
                        .introduce(baby.getIntroduce())
                        .avatar(baby.getAvatar())
                        .build())
                .collect(Collectors.toList());
        return babyVoList;
    }

    public static String calculateAge(Date birthday) {
        if (birthday == null) return "未知";

        // 确保时区与数据库存储一致（假设数据库为GMT+8）
        ZoneId zoneId = ZoneId.of("GMT+8");
        LocalDate birthDate = birthday.toInstant().atZone(zoneId).toLocalDate();
        LocalDate currentDate = LocalDate.now(zoneId);

        // 计算月份差
        long totalMonths = ChronoUnit.MONTHS.between(birthDate, currentDate);
        if (totalMonths >= 12) {
            return (totalMonths / 12) + "岁";
        } else if (totalMonths > 0) {
            return totalMonths + "月";
        }

        // 计算周数差
        long weeks = ChronoUnit.WEEKS.between(birthDate, currentDate);
        if (weeks > 0) {
            return weeks + "周";
        }
        // 处理不足一周的情况
        long days = ChronoUnit.DAYS.between(birthDate, currentDate);
        return days >= 0 ? days + "天" : "未出生";
    }
}
