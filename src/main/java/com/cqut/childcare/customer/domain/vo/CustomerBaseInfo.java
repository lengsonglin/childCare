package com.cqut.childcare.customer.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerBaseInfo
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBaseInfo {

    private String nickName;
    private String gender;
    private int age;
    private String avatar;
    private String type;
}
