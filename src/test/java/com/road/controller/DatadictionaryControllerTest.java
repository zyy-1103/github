package com.road.controller;

import com.alibaba.fastjson.JSONObject;
import com.road.bean.Datadictionary;
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
class DatadictionaryControllerTest {

    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;
    JSONObject object = new JSONObject();

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/datadic/getData")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getXx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/datadic/xx/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void touch() throws Exception {
        Datadictionary datadictionary = new Datadictionary(0, "测试模块", "1", "测试", "1", 0, LocalDate.now(),
                LocalDate.now(), LocalDate.now(), "1");
        object.put("datadictionary", datadictionary);
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/datadic/touch")
                        .content(object.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}