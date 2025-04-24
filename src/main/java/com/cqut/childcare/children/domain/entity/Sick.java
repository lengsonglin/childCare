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
 * @Description 生病记录实体类
 * @Author Faiz
 * @ClassName Sick
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_sick")
public class Sick implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long babyId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    private String type;

    private String symptom;

    private String description;

    private String temperature;

    private String remark;
} 