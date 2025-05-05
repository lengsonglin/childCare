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
 * @Description
 * @Author Faiz
 * @ClassName Vaccination
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_vaccination")
public class Vaccination implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long babyId;
    private String name;
    private Integer count;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
    private Long createBy;
    private String remark;

}
