package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.CleanDto;
import com.cqut.childcare.children.domain.entity.Clean;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName CleanService
 * @Version 1.0
 */
public interface CleanService {
    void addCleanRecord(Long cid, CleanDto cleanDto);

    List<Clean> getCleanRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifyCleanRecord(Long cid,CleanDto cleanDto, Long cleanId);

    ApiResult deleteCleanRecord(Long cid, Long cleanId);
}
