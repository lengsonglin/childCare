package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.GrowthRecordDto;
import com.cqut.childcare.children.domain.entity.GrowthRecord;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description 成长记录服务接口
 * @Author Faiz
 * @ClassName GrowthRecordService
 * @Version 1.0
 */
public interface GrowthRecordService {
    void addGrowthRecord(Long cid, GrowthRecordDto growthRecordDto);

    List<GrowthRecord> getGrowthRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifyGrowthRecord(Long cid, GrowthRecordDto growthRecordDto, Long recordId);

    ApiResult deleteGrowthRecord(Long cid, Long recordId);
} 