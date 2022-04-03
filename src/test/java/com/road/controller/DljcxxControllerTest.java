package com.road.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class DljcxxControllerTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
   public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        session = new MockHttpSession();
        session.setAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY", "root");
        session.setAttribute("id", "1");
    }

    @Test
    void getData() throws Exception {
        JSONObject object = new JSONObject();
        object.put("curr", "1");
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/dljcxx/getData")
                        .content(object.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //返回值是一个数组
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getDljcxxXx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/dljcxx/xx/6")
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void update() throws Exception{
        JSONObject object = new JSONObject();
        object.put("id","2");
        object.put("info", "dlm");
        object.put("data", "成华大道");
        mockMvc.perform(MockMvcRequestBuilders.post("/dljcxx/update")
                        .content(object.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void touch() throws Exception {
        JSONObject object = new JSONObject();
        object.put("cd", "1");
        object.put("hdmkd", "1");
        object.put("hdmkdbz", "1");
        object.put("cdfbbz", "1");
        object.put("qd", "1");
        object.put("fdzxlcount", "1");
        object.put("lnbwcount", "1");
        mockMvc.perform(MockMvcRequestBuilders.post("/dljcxx/touch")
                        .content(object.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}