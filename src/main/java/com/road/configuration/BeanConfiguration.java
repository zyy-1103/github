package com.road.configuration;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.*;

@Configuration
public class BeanConfiguration {

    @Bean(name = "dljcxx")
    public HashMap<String, String> hashMap(){
        HashMap<String, String> map = new HashMap<>();
        String[] a = {"bzp","dljcxx","dlq","gjzd","glss","isglss","jshcd","jsz","jzwcrk","lnbw","lsydbz","rxhdx","ssglbsd","tqlb","xhdlk"};
        String[] b = {"标志牌", "道路基础信息", "导流区", "公交站点", "隔离设施", "安全锥", "减速缓冲带", "警示柱", "建筑物出入口", "路内泊位", "临时移动标志", "人行横道线", "爆闪灯", "突起路标", "信号灯路口"};
        int len=a.length;
        for (int i = 0; i < len; i++) {
            map.put(a[i], b[i]);
        }
        return map;
    }

    @Bean("file")
    public HashMap<String, String> fileMap(){
        return new HashMap<>();
    }

    /**
     * 将hashmap中过期的数据删除
     */
    @Bean
    public ScheduledExecutorService scheduledExecutorService(@Qualifier("file") HashMap<String,String> hashMap,
                                                             RedisAsyncCommands<String,String> commands){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> {
            hashMap.entrySet().iterator().forEachRemaining(e->{
                if (commands.get(e.getKey()) == null) {
                    hashMap.remove(e.getKey());
                }
            });
        }, 0, 60, TimeUnit.SECONDS);
        return service;
    }

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(3, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200, true));
    }

    @Bean
    public ScheduledExecutorService service(@Qualifier("file") HashMap<String,String> map,RedisAsyncCommands<String,String> commands){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleWithFixedDelay(() -> {
            map.keySet().iterator().forEachRemaining(e -> {
                try {
                    if (commands.get(e).get() == null) {
                        File file = new File(e);
                        map.remove(e);
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            });
        }, 0, 60, TimeUnit.SECONDS);
        return service;
    }

    @Bean
    public Session session() throws IOException {
        Connection connection = new Connection("39.105.175.79", 22);
        connection.connect();
        boolean root = connection.authenticateWithPassword("root", "Tf8364334@");
        return connection.openSession();
    }

    @Bean
    public RedisAsyncCommands<String,String> connect() {
        RedisURI redisUri = RedisURI.builder()
                .withHost("39.105.175.79").withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient client = RedisClient.create(redisUri);
       return client.connect().async();
    }
}
