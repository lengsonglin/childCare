package com.cqut.childcare.customer.service.impl;


import com.cqut.childcare.common.constant.RedisKey;
import com.cqut.childcare.common.exception.AppRuntimeException;
import com.cqut.childcare.common.exception.CommonErrorEnum;
import com.cqut.childcare.common.service.SystemSettingLoader;
import com.cqut.childcare.common.utils.HttpUtils;
import com.cqut.childcare.customer.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Faiz
 * @ClassName SmsServiceImpl
 * @Version 1.0
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final TimeUnit timeUnit = TimeUnit.MINUTES;

    @Override
    public void sendCaptcha(String phone) {
        String captcha = redisTemplate.opsForValue().get(RedisKey.getKey(RedisKey.CAPTCHA_KEY,phone));
        if (StringUtils.hasText(captcha)) {
            return;
        }
        // 生成验证码
        String validateCaptcha = RandomStringUtils.randomNumeric(4);
        redisTemplate.opsForValue().set(RedisKey.getKey(RedisKey.CAPTCHA_KEY,phone), validateCaptcha,SystemSettingLoader.getSystemSetting().getShortMsgTime(), timeUnit);
        sendSms(phone, validateCaptcha);
    }

    public void sendSms(String phone, String validateCaptcha) {

        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "1a4de6f66e0b4f958c381f7f0c923495";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+validateCaptcha);
        bodys.put("template_id", "CST_ptdie100");  //注意，CST_ptdie100该模板ID仅为调试使用，调试结果为"status": "OK" ，即表示接口调用成功，然后联系客服报备自己的专属签名模板ID，以保证短信稳定下发
        bodys.put("phone_number", phone);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            log.info(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            throw new AppRuntimeException(CommonErrorEnum.SMS_ERROR);
        }
    }
}

