package com.cqut.childcare.common.transaction.service;

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
@Component
public class MQProducer {

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
        System.out.println(sendResult.getSendStatus().equals(SendStatus.SEND_OK));
    }


}
