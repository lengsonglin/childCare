package com.cqut.childcare.children.service.babyEvent.impl;

import com.cqut.childcare.children.dao.*;
import com.cqut.childcare.children.service.babyEvent.BabyEventCommonService;
import com.cqut.childcare.children.service.babyEvent.BabyEventService;
import com.cqut.childcare.common.domain.dto.PeriodTimeBaseReq;
import com.cqut.childcare.common.exception.AppRuntimeException;
import com.cqut.childcare.common.exception.BabyEventEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BabyEventServiceImpl implements BabyEventService {

    @Autowired
    private BabyEventCommonService commonService;
    
    @Autowired
    private CleanDao cleanDao;
    @Autowired
    private DiningDao diningDao;
    @Autowired
    private SleepDao sleepDao;
    @Autowired
    private DevelopmentCheckRecordDao developmentCheckRecordDao;
    @Autowired
    private GrowthRecordDao growthRecordDao;
    @Autowired
    private SickDao sickDao;
    @Autowired
    private TeethDao teethDao;
    @Autowired
    private VaccinationDao vaccinationDao;

    private final ExecutorService executorService = Executors.newFixedThreadPool(8);

    @Override
    public Map<String, Object> getAllEventRecords(Long cid, PeriodTimeBaseReq periodTimeBaseReq) {
        // 1. 处理时间范围
        final PeriodTimeBaseReq finalPeriodTimeBaseReq = commonService.processTimeRange(periodTimeBaseReq);
        
        // 2. 验证时间跨度
        LocalDate begin = finalPeriodTimeBaseReq.getBeginTime();
        LocalDate end = finalPeriodTimeBaseReq.getEndTime();
        long days = java.time.temporal.ChronoUnit.DAYS.between(begin, end);
        if (days > 14) {
            throw new AppRuntimeException(BabyEventEnum.PERIOD_TIME_OUT);
        }

        // 3. 获取关联客户列表（只查询一次）
        List<Long> relationCustomers = commonService.getRelationCustomers(cid, finalPeriodTimeBaseReq.getBabyId());
        
        // 4. 转换时间边界
        LocalDateTime[] timeBoundary = commonService.convertTimeBoundary(finalPeriodTimeBaseReq);
        final LocalDateTime beginDateTime = timeBoundary[0];
        final LocalDateTime endDateTime = timeBoundary[1];

        // 5. 并行获取各类事件记录
        Map<String, Object> result = new HashMap<>();
        
        CompletableFuture<Void> cleanFuture = CompletableFuture.runAsync(() ->
            result.put("clean", cleanDao.getCleanRecord(finalPeriodTimeBaseReq.getBabyId(),relationCustomers,finalPeriodTimeBaseReq)), executorService);

        CompletableFuture<Void> diningFuture = CompletableFuture.runAsync(() -> 
            result.put("dining", diningDao.getDiningRecord(relationCustomers,finalPeriodTimeBaseReq)), executorService);
            
        CompletableFuture<Void> sleepFuture = CompletableFuture.runAsync(() -> 
            result.put("sleep", sleepDao.getSleepRecord(finalPeriodTimeBaseReq.getBabyId(),relationCustomers,finalPeriodTimeBaseReq)), executorService);
            
        CompletableFuture<Void> developmentFuture = CompletableFuture.runAsync(() -> 
            result.put("developmentCheckRecord", developmentCheckRecordDao.getDevelopmentCheckRecord(finalPeriodTimeBaseReq.getBabyId(),relationCustomers,finalPeriodTimeBaseReq)), executorService);
            
        CompletableFuture<Void> growthFuture = CompletableFuture.runAsync(() -> 
            result.put("growthRecord", growthRecordDao.getGrowthRecord(finalPeriodTimeBaseReq.getBabyId(),relationCustomers,finalPeriodTimeBaseReq)), executorService);
            
        CompletableFuture<Void> sickFuture = CompletableFuture.runAsync(() -> 
            result.put("sick", sickDao.getSickRecord(finalPeriodTimeBaseReq.getBabyId(),relationCustomers,finalPeriodTimeBaseReq)), executorService);
            
        CompletableFuture<Void> teethFuture = CompletableFuture.runAsync(() -> 
            result.put("teeth", teethDao.getTeethRecord(finalPeriodTimeBaseReq.getBabyId(),relationCustomers,finalPeriodTimeBaseReq)), executorService);
            
        CompletableFuture<Void> vaccinationFuture = CompletableFuture.runAsync(() -> 
            result.put("vaccination", vaccinationDao.getVaccinationRecord(finalPeriodTimeBaseReq.getBabyId(),relationCustomers,finalPeriodTimeBaseReq)), executorService);

        // 等待所有异步任务完成
        CompletableFuture.allOf(cleanFuture, diningFuture, sleepFuture, developmentFuture,
                growthFuture, sickFuture, teethFuture, vaccinationFuture).join();

        return result;
    }
} 