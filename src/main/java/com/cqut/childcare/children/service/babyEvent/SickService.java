package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.SickDto;
import com.cqut.childcare.children.domain.entity.Sick;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description 生病记录服务接口
 * @Author Faiz
 * @ClassName SickService
 * @Version 1.0
 */
public interface SickService {
    void addSickRecord(Long cid, SickDto sickDto);

    List<Sick> getSickRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifySickRecord(Long cid, SickDto sickDto, Long sickId);

    ApiResult deleteSickRecord(Long cid, Long sickId);

    Sick getSickRecordOne(Long recordId);
}