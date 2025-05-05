package com.cqut.childcare.children.service.babyEvent;

import com.cqut.childcare.children.domain.dto.babyEvent.DiningDto;
import com.cqut.childcare.children.domain.entity.Dining;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.domain.vo.ApiResult;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName DiningService
 * @Version 1.0
 */
public interface DiningService {
    void addDiningRecord(Long cid, DiningDto diningDto);

    List<Dining> getDiningRecord(Long cid, PeriodTimeBaseReq periodTimeBaseReq);

    ApiResult modifyDiningRecord(Long cid, DiningDto diningDto, Long diningId);

    ApiResult deleteDiningRecord(Long cid, Long diningId);

    Dining getDiningRecordOne(Long cid,Long recordId);
}
