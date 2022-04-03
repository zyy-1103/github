package com.road.service;

import com.alibaba.fastjson.JSONObject;
import com.road.bean.Operator;
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
class OperatorServiceTest {

    @Autowired
    OperatorService service;

    @Test
    void getData() {
        String data = service.getData();
        String data1 = JSONObject.parseObject(data).getString("data");
        Assertions.assertNotNull(data1);
    }

    @Test
    void getXx() {
        String xx = service.getXx("1");
        Assertions.assertNotNull(JSONObject.parseObject(xx).getString("data"));
    }

    @Test
    void update() {
        JSONObject object = new JSONObject();
        object.put("id", "1");
        object.put("info", "xtgl");
        object.put("data", "2");
        Assertions.assertEquals(service.update(object.toJSONString()), "1");
    }

    @Test
    void touch() {
        Parse parse = new Parse();
        Operator operator = new Operator(0, "toor", "1", 1, "1", "1", "1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "0", "1", null);
        parse.setOperator(operator);
        Assertions.assertEquals(service.touch(parse), "1");
    }
}