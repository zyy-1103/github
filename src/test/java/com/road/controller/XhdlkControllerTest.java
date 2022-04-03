package com.road.controller;

import com.alibaba.fastjson.JSONObject;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class XhdlkControllerTest {

    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getXhdlk() throws Exception {
        JSONObject object = new JSONObject();
        object.put("tableName", "xhdlk");
        mockMvc.perform(MockMvcRequestBuilders.post("/dljcxx/xx/6/getData")
                        .content(object.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void update() throws Exception {
        JSONObject object = new JSONObject();
        object.put("tableName","xhdlk");
        object.put("id", "1");
        object.put("info", "lkmc");
        object.put("data", "菲伊路口");
        mockMvc.perform(MockMvcRequestBuilders.post("/dljcxx/xx/6/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object.toJSONString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getXhdlkXx() {
    }
}