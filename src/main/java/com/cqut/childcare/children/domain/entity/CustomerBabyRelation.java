package com.cqut.childcare.children.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerBabyRelation
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_customer_baby_relation")
public class CustomerBabyRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long babyId;

    private Long customerId;

    private Integer relationship;

    private String remark;
}
