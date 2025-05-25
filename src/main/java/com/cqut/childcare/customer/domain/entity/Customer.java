package com.cqut.childcare.customer.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName Customer
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String telPhone;
    private String nickName;

    private String gender;
    private String password;

    private int age;

    private String avatar;

    private String realName;

    @TableField("id_number")
    private String IDNumber;

    private String email;
    private String address;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerTime;
    /**
     * 用户类型（family/childcareWorker）
     */
    private String type;

}
