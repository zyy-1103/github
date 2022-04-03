package com.road.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

//@Component
//@RocketMQMessageListener(consumerGroup = "springBootGroup", topic = "TestTopic")
public class SearchConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("message:" + s);
    }
}
