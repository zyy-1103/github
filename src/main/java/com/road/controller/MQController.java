package com.road.controller;

import com.road.service.SpringProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MQController {

    @Resource
    private SpringProducer springProducer;

    @GetMapping("/t")
    public String sendMessage(@RequestParam("message") String message){
        springProducer.sendMessage("DelTopic", message);
        return "消息发送完成";
    }
}