package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.SleepDto;
import com.cqut.childcare.children.domain.entity.Sleep;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description 睡眠记录服务接口
 * @Author Faiz
 * @ClassName SleepService
 * @Version 1.0
 */
public interface SleepService {
    void addSleepRecord(Long cid, SleepDto sleepDto);

    List<Sleep> getSleepRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifySleepRecord(Long cid, SleepDto sleepDto, Long sleepId);

    ApiResult deleteSleepRecord(Long cid, Long sleepId);

    Sleep getSleepRecordOne(Long recordId);
}