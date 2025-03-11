package com.cqut.childcare.quartz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Description
 * @Author Faiz
 * @ClassName QrtzJobDto
 * @Version 1.0
 */
@Data
public class QrtzJobDto {

    private String jobName;
    private String className;
    private String cronExp;
    /**
     *   状态，runing/paused
     */
    private String status;

    private Map<String,Object> jobParams;

    private String description;
}
