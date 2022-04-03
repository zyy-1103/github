package com.road.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Component
public class SearchProducer {
//    @Resource
    RocketMQTemplate template;

    public void sendMsg(String topic, String msg) {
        template.convertAndSend(topic, msg);
    }
}
