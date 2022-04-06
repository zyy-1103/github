package com.road.service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroup", topic = "DelTopic")
public class SpringConsumer implements RocketMQListener<String> {

    @Autowired
    RedisAsyncCommands<String, String> commands;
    @Autowired
    @Qualifier("file")
    HashMap<String, String> map;
    @Autowired
    ScheduledExecutorService service;

    @Override
    public void onMessage(String msg) {
        try {
            Connection connection = new Connection("39.105.175.79", 22);
            connection.connect();
            connection.authenticateWithPassword("root", "Tf8364334@");
            Session session = connection.openSession();
            session.execCommand("rm " + msg);
            InputStream stdout = session.getStdout();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.submit(()->{
            System.out.println("Hello My Executor");
        });
    }
}
