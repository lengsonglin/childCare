package com.cqut.childcare.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonErrorEnum implements ErrorEnum {

    SYSTEM_ERROR(500, "系统出小差了，请稍后再试哦~~"),
    INVALID_TOKEN(401,"访问令牌不合法"),
    TELPHONE_UNREGISTERED(403,"当前电话号码未注册"),
    UPLOAD_PICTURE_ERROR(1000,"上传图片失败"),

    SMS_ERROR(1001,"短信验证码获取失败"),
    INVALID_CAPTCHA(1002,"短信验证码无效"),
    CUSTOMER_PHONE_IS_REGISTER(1003,"手机号已注册"),
    GET_AVATAR_URL_FAILED(1004,"获取头像失败"),

    PARAM_VALID(2000, "参数校验失败{0}"),
    BUSINESS_ERROR(2001, "{0}");



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
