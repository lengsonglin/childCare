package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author Faiz
 * @ClassName DevelopmentCheck
 * @Version 1.0
 */
@Data
@TableName("t_development_check")
public class DevelopmentCheck implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String time;

    private String description;

    private Long createBy;

    private String remark;

}
