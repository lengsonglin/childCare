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

    CHILDCARE_WORKER("childcareWorker");
    private final String type;
}
