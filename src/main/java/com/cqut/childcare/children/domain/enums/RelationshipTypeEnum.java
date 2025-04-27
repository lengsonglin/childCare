package com.cqut.childcare.children.domain.enums;

import com.cqut.childcare.common.domain.enums.YesOrNoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description 用户与幼儿关系枚举
 * @Author Faiz
 * @ClassName RelationShipType
 * @Version 1.0
 */
    @AllArgsConstructor
    @Getter
public enum RelationshipTypeEnum {

    MAIN_FAMILY(0,"绑定孩子的第一个家长"),

    CHILDCARE_WORKER(1,"保育员"),

    SUB_FAMILY(2,"孩子的其他家长");

    private final Integer type;

    private final String desc;

    private static Map<Integer, RelationshipTypeEnum> cache;

    static {
        cache = Arrays.stream(RelationshipTypeEnum.values()).collect(Collectors.toMap(RelationshipTypeEnum::getType, Function.identity()));
    }

    public static RelationshipTypeEnum of(Integer type) {
        return cache.get(type);
    }

}
