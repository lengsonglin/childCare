package com.cqut.childcare.common.exception;

import lombok.Data;

/**
 * @Description
 * @Author Faiz
 * @ClassName AppRuntimeException
 * @Version 1.0
 */
@Data
public class AppRuntimeException extends RuntimeException{
    private Integer code ;
    private String msg ;

    public AppRuntimeException(ErrorEnum errorEnum){
        super(errorEnum.getErrorMsg());
        this.code = errorEnum.getErrorCode();
        this.msg = errorEnum.getErrorMsg();
    }

    public AppRuntimeException(String msg){
        super(msg);
        this.msg = msg;     }
    public AppRuntimeException(int code , String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
