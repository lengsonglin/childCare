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
 * @Description 饮食记录实体类
 * @Author Faiz
 * @ClassName Dining
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_dining")
public class Dining implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long babyId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date beginTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private String amount;
    /*
    食物类型，甜点，可以自己输入
     */
    private String type;

    private String photoUrl;

    private Long createBy;

    private String remark;
} 