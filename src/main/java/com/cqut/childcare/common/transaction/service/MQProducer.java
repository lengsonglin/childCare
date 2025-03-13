package com.cqut.childcare.common.transaction.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Faiz
 * @ClassName MQProducer
 * @Version 1.0
 */
@Slf4j
@Component
public class MQProducer {

    public final Long TIMEOUT = 3000L;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步消息
     * @param topic
     * @param body
     */
    public void sendSyncMessage(String topic,Object body){
        Message<Object> message = MessageBuilder.withPayload(body).build();
        SendResult sendResult = rocketMQTemplate.syncSend(topic, message);
    }

    public void sendAsyncMessage(String topic,Object body){
        Message<Object> message = MessageBuilder.withPayload(body).build();
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                throw new RuntimeException("异步消息发送失败");
            }
        });
    }

    /**
     * // RocketMQ 预设延迟级别：1s, 5s, 10s, 30s, 1m, 2m, 3m, 4m, 5m, 6m, 7m, 8m, 9m, 10m, 20m, 30m, 1h, 2h
     * @param topic
     * @param body
     */

    public void sendDelayedMessage(String topic,Object body,int DelayLevel){
        Message<Object> message = MessageBuilder.withPayload(body).build();
        rocketMQTemplate.syncSend(topic,message,TIMEOUT,DelayLevel);
    }


}
