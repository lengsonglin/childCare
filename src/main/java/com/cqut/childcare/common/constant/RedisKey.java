package com.cqut.childcare.common.constant;

/**
 * @Description
 * @Author Faiz
 * @ClassName RedisKey
 * @Version 1.0
 */
public class RedisKey {

    private static final String BASE_KEY = "childcare:";

    /*
     用户相关
     */
    //验证码
    public static final String CAPTCHA_KEY = "captcha:phone_%s";
    //token
    public static final String CUSTOMER_TOKEN = "customerToken:cid_%d";
    //用户信息
    public static final String CUSTOMER_INFO = "customerInfo:cid_%d";

    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }
}
