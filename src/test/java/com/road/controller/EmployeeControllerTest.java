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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeControllerTest {

    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getData() throws Exception {
        JSONObject object = new JSONObject();
        object.put("n", "1");
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/employee/getData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object.toJSONString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getPages() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/employee/getPages"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getXx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/employee/xx/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void update() throws Exception {
        JSONObject object = new JSONObject();
        object.put("id","1");
        object.put("info", "xb");
        object.put("data", "男");
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/employee/update")
                        .content(object.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void del() throws Exception {
        JSONObject object = new JSONObject();
        String[] strings = {"18"};
        object.put("strings", strings);
        mockMvc.perform(MockMvcRequestBuilders.post("/xtgl/employee/del")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object.toJSONString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}