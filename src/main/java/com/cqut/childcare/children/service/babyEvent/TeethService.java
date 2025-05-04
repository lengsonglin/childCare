package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.TeethDto;
import com.cqut.childcare.children.domain.entity.Teeth;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description 长牙记录服务接口
 * @Author Faiz
 * @ClassName TeethService
 * @Version 1.0
 */
public interface TeethService {
    void addTeethRecord(Long cid, TeethDto teethDto);

    List<Teeth> getTeethRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifyTeethRecord(Long cid, TeethDto teethDto, Long teethId);

    ApiResult deleteTeethRecord(Long cid, Long teethId);
} 