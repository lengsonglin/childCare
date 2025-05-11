package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.Clean;
import com.cqut.childcare.children.domain.entity.Dining;
import com.cqut.childcare.children.mapper.DiningMapper;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName DiningDao
 * @Version 1.0
 */
@Service
public class DiningDao extends ServiceImpl<DiningMapper, Dining> {


    public List<Dining> getDiningRecord(List<Long> relationCustomers, PeriodTimeBaseReq periodTimeBaseReq) {

        // 提取时间段参数
        LocalDate beginDate = periodTimeBaseReq != null ? periodTimeBaseReq.getBeginTime() : null;
        LocalDate endDate = periodTimeBaseReq != null ? periodTimeBaseReq.getEndTime() : null;

        // 转换时间边界（处理LocalDate到LocalDateTime的转换）
        LocalDateTime beginDateTime = beginDate != null ? beginDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        return lambdaQuery()
                .eq(Dining::getBabyId,periodTimeBaseReq.getBabyId())
                .in(Dining::getCreateBy,relationCustomers)
                .ge(beginDateTime != null, Dining::getBeginTime, beginDateTime)
                .le(endDateTime != null, Dining::getBeginTime, endDateTime)
                .list();
    }

    public Dining getDiningRecordOne(Long recordId) {
        return lambdaQuery()
                .eq(Dining::getId,recordId)
                .one();
    }
}