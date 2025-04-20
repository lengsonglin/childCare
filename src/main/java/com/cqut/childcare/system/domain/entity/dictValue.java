package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author Faiz
 * @ClassName dictValue
 * @Version 1.0
 */
@Data
@TableName("t_dict_value")
public class dictValue {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Long createBy;

    private Long dictKeyId;
    private String remark;
}
