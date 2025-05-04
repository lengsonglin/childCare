package com.cqut.childcare.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyEventEnum
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum BabyEventEnum implements ErrorEnum{
    PERMISSION_EXCEEDED(4000,"无法删除或更新其他家人添加的事件"),
    PERMISSION_ERROR(4001,"当前家长与当前孩子无亲属关系！"),
    PERIOD_TIME_OUT(4003,"时间间隔最多为7天"),
    BABY_NOT_EXIST(4002,"宝宝不存在")
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
