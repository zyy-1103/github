package com.road.service;

import com.alibaba.fastjson.JSONObject;
import com.road.bean.Jfodule;
import com.road.bean.Parse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ModuleServiceTest {

    @Autowired
    ModuleService service;

    @Test
    void getData() {
        String data = service.getData();
        JSONObject jsonObject = JSONObject.parseObject(data);
        Assertions.assertNotNull(jsonObject.getString("data"));
    }

    @Test
    void getXx() {
        String xx = service.getXx("1");
        Assertions.assertNotNull(JSONObject.parseObject(xx).getString("data"));
    }

    @Test
    void update() {
        JSONObject object = new JSONObject();
        object.put("id", "8");
        object.put("info", "mksm");
        object.put("data", "新的说明");
        String update = service.update(object.toJSONString());
        Assertions.assertEquals(update, "1");
    }

    @Test
    void touch() {
        Parse parse = new Parse();
        Jfodule jfodule = new Jfodule(0, 1, "测试", "测试", "1", "1", "1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "1");
        parse.setJfodule(jfodule);
        String touch = service.touch(parse);
        Assertions.assertEquals(touch, "1");
    }
}