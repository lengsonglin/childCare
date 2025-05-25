package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.customer.service.CustomerRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BabyEventCommonService {
    
    @Autowired
    private CustomerRelationService customerRelationService;
    
    /**
     * 获取关联客户列表
     */
    public List<Long> getRelationCustomers(Long cid, Long babyId) {
        return customerRelationService.getRelationCustomer(cid, babyId);
    }
    
    /**
     * 处理时间范围
     */
    public PeriodTimeBaseReq processTimeRange(PeriodTimeBaseReq periodTimeBaseReq) {
        LocalDate begin = periodTimeBaseReq.getBeginTime();
        LocalDate end = periodTimeBaseReq.getEndTime();
        
        if (begin == null || end == null) {
            end = LocalDate.now();
            begin = end.minusDays(14);
        }
        
        periodTimeBaseReq.setBeginTime(begin);
        periodTimeBaseReq.setEndTime(end);
        return periodTimeBaseReq;
    }
    
    /**
     * 转换时间边界
     */
    public LocalDateTime[] convertTimeBoundary(PeriodTimeBaseReq periodTimeBaseReq) {
        LocalDate beginDate = periodTimeBaseReq.getBeginTime();
        LocalDate endDate = periodTimeBaseReq.getEndTime();
        
        LocalDateTime beginDateTime = beginDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        
        return new LocalDateTime[]{beginDateTime, endDateTime};
    }
} 