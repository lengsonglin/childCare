package com.cqut.childcare.children.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 成长记录实体类
 * @Author Faiz
 * @ClassName GrowthRecord
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_growth_record")
public class GrowthRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long babyId;

    private String height;

    private String weight;

    private String headCircumference;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    private Long createBy;
} 