package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 发育检查实体类
 * @Author Faiz
 * @ClassName DevelopmentCheck
 * @Version 1.0
 */
@Data
@TableName("t_development_check")
public class DevelopmentCheck implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String time;
    private String description;
    private String remark;

}
