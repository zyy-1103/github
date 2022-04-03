package com.road.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.OperatorModule;
import com.road.bean.Parse;
import com.road.mapper.OperatorModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorModuleService {
    @Autowired
    OperatorModuleMapper mapper;
    JSONObject object = new JSONObject();

    public String getData(){
        object.clear();
        object.put("data", mapper.selectAll());
        return object.toJSONString();
    }

    public String update(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String id = jsonObject.getString("id");
        String info = jsonObject.getString("info");
        String data = jsonObject.getString("data");
        return String.valueOf(mapper.updateInfo(id, info, data));
    }

    public String del(String s) {
        JSONArray strings = JSONObject.parseObject(s).getJSONArray("strings");
        int size = strings.size();
        for (int i = 0; i < size; i++) {
            mapper.deleteById(strings.getString(i));
        }
        return "1";
    }

    public String getXx(String id) {
        object.clear();
        object.put("data", mapper.selectOne(id));
        return object.toJSONString();
    }

    public String touch(Parse parse) {
        OperatorModule operatorModule = parse.getOperatorModule();
        mapper.insert(operatorModule);
        return "1";
    }
}
