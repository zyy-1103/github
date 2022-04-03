package com.road.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class XhdlkServiceTest {

    @Autowired
    XhdlkService service;
    @Test
    void getData() {
        JSONObject object = new JSONObject();
        object.put("tableName", "xhdlk");
        String xhdlk = service.getData("1", object.toJSONString());
        Assertions.assertNotNull(xhdlk);
    }

    @Test
    void getXhdlkXx() {
        String xhdlk = service.getXhdlkXx("1", "xhdlk");
        Assertions.assertNotNull(xhdlk);
    }

    @Test
    void update() {
        JSONObject object = new JSONObject();
        object.put("id", "1");
        object.put("info", "dd");
        object.put("data", "jsu");
        String update = service.update(object.toJSONString());
        Assertions.assertEquals(update, "1");
    }
}