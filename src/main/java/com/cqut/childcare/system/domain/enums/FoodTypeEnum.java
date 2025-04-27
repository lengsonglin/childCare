package com.cqut.childcare.system.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author Faiz
 * @ClassName FoodTypeEnum
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum FoodTypeEnum {
    REGISTER(1,"注册"),
    MODIFY_PASSWORD(2,"修改密码")
    ;
    private final Integer type;

    private final String desc;
}



