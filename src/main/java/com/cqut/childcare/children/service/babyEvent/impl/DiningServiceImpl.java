package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.CustomerBabyRelationDao;
import com.cqut.childcare.children.dao.DiningDao;
import com.cqut.childcare.children.domain.dto.babyEvent.DiningDto;
import com.cqut.childcare.children.domain.entity.Clean;
import com.cqut.childcare.children.domain.entity.Dining;
import com.cqut.childcare.children.service.babyEvent.DiningService;
import com.cqut.childcare.common.constant.MinioBucketConstant;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.BabyEventEnum;
import com.cqut.childcare.customer.service.CustomerRelationService;
import com.cqut.childcare.minIo.service.OssService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName DiningServiceImpl
 * @Version 1.0
 */
@Service
public class DiningServiceImpl implements DiningService {

    @Autowired
    private DiningDao diningDao;

    @Autowired
    private OssService ossService;

    @Autowired
    private CustomerRelationService customerRelationService;

    @Autowired
    private CustomerBabyRelationDao customerBabyRelationDao;


    @Override
    public void addDiningRecord(Long cid, DiningDto diningDto) {
        Dining dining = new Dining();
        BeanUtils.copyProperties(diningDto,dining);
        dining.setCreateBy(cid);
        if(ObjectUtils.isNotEmpty(diningDto.getPhoto())){
            String url = ossService.uploadFile(diningDto.getPhoto(), MinioBucketConstant.BABY_DINING_BUCKET);
            dining.setPhotoUrl(url);
        }
        diningDao.save(dining);
    }

    @Override
    public List<Dining> getDiningRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        List<Long> relationCustomers = customerRelationService.getRelationCustomer(cid, periodTimeBaseReq.getBabyId());
        List<Dining> data = diningDao.getDiningRecord(relationCustomers,periodTimeBaseReq);
        return data;
    }

    @Override
    public ApiResult modifyDiningRecord(Long cid, DiningDto diningDto, Long diningId) {
        //查看当前记录是否是当前用户添加
        Dining record = diningDao.getById(diningId);
        if(ObjectUtils.isNotEmpty(record)){
            if(!record.getCreateBy().equals(cid)){
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            Dining temp = new Dining();
            BeanUtils.copyProperties(diningDto,temp);
            temp.setBabyId(record.getBabyId());
            temp.setId(diningId);
            temp.setPhotoUrl(record.getPhotoUrl());
            //照片是否更新
            if(ObjectUtils.isNotEmpty(diningDto.getPhoto())){
                String url = ossService.uploadFile(diningDto.getPhoto(), MinioBucketConstant.BABY_DINING_BUCKET);
                if(StringUtils.isNotBlank(record.getPhotoUrl())){
                    ossService.removeFile(record.getPhotoUrl(),MinioBucketConstant.BABY_DINING_BUCKET);
                }
                temp.setPhotoUrl(url);

            }
            diningDao.updateById(temp);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteDiningRecord(Long cid, Long diningId) {
        Dining record = diningDao.getById(diningId);
        if(ObjectUtils.isNotEmpty(record)){
            if(!record.getCreateBy().equals(cid)){
                return ApiResult.fail(BabyEventEnum.PERMISSION_EXCEEDED);
            }
            diningDao.removeById(diningId);
        }
        return ApiResult.success();
    }
}
