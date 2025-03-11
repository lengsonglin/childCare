package com.cqut.childcare.common.exception;

import lombok.Data;

/**
 * @Description
 * @Author Faiz
 * @ClassName QrtzJobException
 * @Version 1.0
 */
@Data
public class QrtzJobException extends RuntimeException{

    private Integer errorCode;

    private String errorMsg;

    public QrtzJobException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public QrtzJobException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public QrtzJobException(Throwable cause, Integer errorCode, String errorMsg) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
