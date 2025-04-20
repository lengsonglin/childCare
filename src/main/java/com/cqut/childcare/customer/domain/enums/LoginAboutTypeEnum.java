package com.cqut.childcare.customer.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author Faiz
 * @ClassName LoginAboutTypeEnum
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum LoginAboutTypeEnum {
    REGISTER(1,"注册"),
    MODIFY_PASSWORD(2,"修改密码")
    ;
    private final Integer type;

    private final String desc;

}
