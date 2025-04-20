package com.cqut.childcare.customer.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerTypeEnum
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum CustomerTypeEnum {

    FAMILY("family"),

    CAREGIVER("caregiver");
    private final String type;
}
