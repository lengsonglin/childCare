package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.DevelopmentCheckRecordDto;
import com.cqut.childcare.children.domain.entity.DevelopmentCheckRecord;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description 发展检核记录服务接口
 * @Author Faiz
 * @ClassName DevelopmentCheckRecordService
 * @Version 1.0
 */
public interface DevelopmentCheckRecordService {
    void addDevelopmentCheckRecord(Long cid, DevelopmentCheckRecordDto developmentCheckRecordDto);

    List<DevelopmentCheckRecord> getDevelopmentCheckRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifyDevelopmentCheckRecord(Long cid, DevelopmentCheckRecordDto developmentCheckRecordDto, Long recordId);

    ApiResult deleteDevelopmentCheckRecord(Long cid, Long recordId);

    DevelopmentCheckRecord getDevelopmentCheckRecordOne(Long recordId);
}