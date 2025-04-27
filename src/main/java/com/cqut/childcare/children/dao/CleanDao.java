package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.Clean;
import com.cqut.childcare.children.mapper.CleanMapper;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CleanDao
 * @Version 1.0
 */
@Service
public class CleanDao extends ServiceImpl<CleanMapper, Clean> {
    public List<Clean> getCleanRecord(Long babyId, List<Long> relationCustomers, PeriodTimeBaseReq periodTimeBaseReq) {
        // 提取时间段参数
        LocalDate beginDate = periodTimeBaseReq != null ? periodTimeBaseReq.getBeginTime() : null;
        LocalDate endDate = periodTimeBaseReq != null ? periodTimeBaseReq.getEndTime() : null;

        // 转换时间边界（处理LocalDate到LocalDateTime的转换）
        LocalDateTime beginDateTime = beginDate != null ? beginDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;

        return lambdaQuery()
                .eq(Clean::getBabyId,babyId)
                .in(Clean::getCreateBy,relationCustomers)
                .ge(beginDateTime != null, Clean::getTime, beginDateTime)
                .le(endDateTime != null, Clean::getTime, endDateTime)
                .list();
    }
}