package com.road.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class BeanConfiguration {

    @Bean
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
}
