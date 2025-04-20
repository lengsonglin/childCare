package com.cqut.childcare.quartz.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.cqut.childcare.common.handler.JsonToMapHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Description
 * @Author Faiz
 * @ClassName QrtzJob
 * @Version 1.0
 */
@Data
@TableName("t_qrtz_job")
@AllArgsConstructor
@NoArgsConstructor
public class QrtzJob implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String jobName;
    private String className;
    private String cronExp;
    /**
     *   状态，RUNNING/PAUSED
     */
    private String status;

    @TableField(value ="job_params",typeHandler = JsonToMapHandler.class)
    private Map<String,Object> jobParams;

    private Long exeCount;

    private LocalDateTime lastExeTime;

    private String description;

}
