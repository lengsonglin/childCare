package com.cqut.childcare.children.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.childcare.children.domain.entity.GrowthRecord;
import com.cqut.childcare.children.mapper.GrowthRecordMapper;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @Description 成长记录数据访问层
 * @Author Faiz
 * @ClassName GrowthRecordDao
 * @Version 1.0
 */
@Service
public class GrowthRecordDao extends ServiceImpl<GrowthRecordMapper, GrowthRecord> {
    public List<GrowthRecord> getGrowthRecord(Long babyId, List<Long> relationCustomers, PeriodTimeBaseReq periodTimeBaseReq) {
        // 提取时间段参数
        LocalDate beginDate = periodTimeBaseReq != null ? periodTimeBaseReq.getBeginTime() : null;
        LocalDate endDate = periodTimeBaseReq != null ? periodTimeBaseReq.getEndTime() : null;

        // 转换时间边界（处理LocalDate到LocalDateTime的转换）
        LocalDateTime beginDateTime = beginDate != null ? beginDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;

        return lambdaQuery()
                .eq(GrowthRecord::getBabyId, babyId)
                .in(GrowthRecord::getCreateBy, relationCustomers)
                .ge(beginDateTime != null, GrowthRecord::getTime, beginDateTime)
                .le(endDateTime != null, GrowthRecord::getTime, endDateTime)
                .list();
    }
} 