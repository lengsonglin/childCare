package com.cqut.childcare.children.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author Faiz
 * @ClassName CleanTypeEnum
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum CleanTypeEnum {

    BUTT_CLEAN("屁股清洁"),

    ORAL_CAVITY("口腔清洁"),

    SHOWER("洗澡");


    private final String type;
}
