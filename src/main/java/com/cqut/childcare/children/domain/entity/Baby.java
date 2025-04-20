package com.cqut.childcare.children.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName Baby
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_baby")
public class Baby implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    /**
     *  男
     *  女
     *  未知
     */
    @TableField("gender")
    private String gender;

    @TableField("birthday")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthday;

    @TableField("introduce")
    private String introduce;

    @TableField("avatar")
    private String avatar;

    @TableField("create_time")
    private String createTime;

    /**
     *  0  正常
     *  1  删除
     */
    @TableField("is_delete")
    private Integer isDelete;

}
