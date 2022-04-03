package com.road.controller;

import com.alibaba.fastjson.JSONObject;
import com.road.bean.Datadictionaryitem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class DatadictionaryitemControllerTest {

    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/datadicitem/getData")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void touch() throws Exception {
        Datadictionaryitem item = new Datadictionaryitem(0, "信号灯路口", "1", "1", "lkmc",
                "道路基础信息", "1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "0");
        String[] mcs = {"唯一标识符","道路名","所属大队","所属中队","道路属性","道路分类等级","长度","横断面宽度","横断面宽度备注","横断面结构","横断面结构备注","车道分布","车道分布备注","设计速度","方向","起点","终点","信号灯路口数量","非灯控支小路数量","路内泊位数量","公交站点数量","人行横道数量","建筑物出入口数量","标志牌数量","导流区数量","警示柱数量","减速缓冲带数量","突起路标数量","爆闪灯数量","安全锥数量","临时移动标志数量","其他交通设施数量","新增时间","修改时间","删除时间","删除标记","办理人","IP地址"};
        String[] sps = {"id","dlm","ssdd","sszd","dlsx","dlfldj","cd","hdmkd","hdmkdbz","hdmjg","hdmjgbz","cdfb","cdfbbz","sjsd","fx","qd","zd","xhdlkcount","fdzxlcount","lnbwcount","glsscount","gjzdcou","rxhdxcount","jzwcrkcount","bzpcount","dlqcount","jszcoun","jshcdcount","tqlbcount","bsdcoun","aqzcount","lsydbzcount","qtjtsscou","xzsj","xgsj","scsj","isdel","blr","ip"};
        
        JSONObject object = new JSONObject();
        int length = mcs.length;
        for (int i = 0; i < length; i++) {
            item.setMc(mcs[i]);
            item.setSp(sps[i]);
            object.put("datadictionaryitem", item);
            mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/datadicitem/touch")
                            .content(object.toJSONString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

    }
}