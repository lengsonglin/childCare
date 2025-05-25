package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import java.util.Map;

public interface BabyEventService {
    /**
     * 获取所有事件记录
     */
    Map<String, Object> getAllEventRecords(Long cid, PeriodTimeBaseReq periodTimeBaseReq);
} 