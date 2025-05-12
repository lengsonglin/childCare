package com.cqut.childcare.children.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyDetailVo
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BabyDetailVo {

    private Long id;

    private String name;

    private String gender;

    private Date birthday;

    private String age;

    private String introduce;

    private String avatar;

    private String height;

    private String weight;

    private String headCircumference;

    /*
    体重(kg)除以身高(m)的平方
     */
    private String BMI;

}
