package com.cqut.childcare.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.management.relation.Relation;

/**
 * @Description
 * @Author Faiz
 * @ClassName RelationEnum
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum RelationErrorEnum implements ErrorEnum{
    INVALID_TELPHONE(3000,"当前手机号未绑定用户或者手机号无效"),

    ADD_FAMILY_ERROR(3001,"请先绑定孩子或者确认自己是第一家长");
    ;
    private final Integer code;
    private final String msg;
    @Override
    public Integer getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMsg() {
        return this.msg;
    }
}
