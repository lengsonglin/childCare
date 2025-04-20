package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 功能实体类
 * @Author Faiz
 * @ClassName Function
 * @Version 1.0
 */
@Data
@TableName("t_function")
public class Function implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Byte onState;

    private String description;

    private String remark;
}
