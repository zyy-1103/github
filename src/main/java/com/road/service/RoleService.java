package com.road.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.Parse;
import com.road.bean.Role;
import com.road.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleMapper mapper;
    JSONObject object = new JSONObject();
    public String getData(){
        object.clear();
        object.put("data", mapper.selectAll());
        return object.toJSONString();
    }

    public String getXx(String id) {
        object.clear();
        object.put("data", mapper.selectById(id));
        return object.toJSONString();
    }

    public String del(String s) {
        JSONArray strings = JSONObject.parseObject(s).getJSONArray("strings");
        int size = strings.size();
        for (int i = 0; i < size; i++) {
            mapper.deleteById(strings.getString(i));
        }
        return "1";
    }

    public String update(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String id = jsonObject.getString("id");
        String info = jsonObject.getString("info");
        String data = jsonObject.getString("data");
        return String.valueOf(mapper.update(id, info, data));
    }

    public String touch(Parse parse) {
        Role role = parse.getRole();
        return String.valueOf(mapper.insert(role));
    }
}
