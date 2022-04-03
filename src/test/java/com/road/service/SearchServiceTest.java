package com.road.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class SearchServiceTest {

    @Autowired
    SearchService service;

    @Test
    void getAll() {
        JSONObject object = new JSONObject();
        object.put("grade", "sszd");
        System.out.println(service.getAll(object.toJSONString()));
    }

    @Test
    void getCondition(){
        JSONObject object = new JSONObject();
        object.put("grade", "sszd");
        object.put("orgName", "筑梦中队");
        object.put("dlInfo", "dlm");
        object.put("dlData", "jsu");
        String[] t = {"bzp"};
        String[] y = {">"};
        String[] d = {"0"};
        object.put("t", t);
        object.put("y", y);
        object.put("d", d);
        String[] s = {"bzp", "dlq", "gjzd"};
        object.put("showList", s);
        String byCondition = service.getByCondition(object.toJSONString());
        System.out.println(byCondition);
    }
}