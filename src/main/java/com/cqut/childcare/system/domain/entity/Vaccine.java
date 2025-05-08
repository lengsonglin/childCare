package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author Faiz
 * @ClassName Vaccine
 * @Version 1.0
 */
@Data
@TableName("t_vaccine")
public class Vaccine implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String type;

    private String vaccinationTime;

    private String name;

    private String description;

    private String attention;

}
