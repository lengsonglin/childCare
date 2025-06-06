package com.cqut.childcare.children.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyVo
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BabyVo {
    private Long id;

    private String name;

    private String gender;

    private String age;

    private Date birthday;

    private String introduce;

    private String avatar;

}
