package com.road.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public static final int PER_PAGE=15;

    @Autowired
    EmployeeMapper mapper;

    JSONObject object = new JSONObject();

    public String getData(String string) {
        string= JSONObject.parseObject(string).getString("n");
        int i = Integer.parseInt(string) * PER_PAGE;
        object.clear();
        object.put("data", mapper.selectAll(i-1, PER_PAGE));
        return object.toJSONString();
    }

    public String getPages() {
        int i = mapper.selectPages();
        i = i / PER_PAGE + (i % PER_PAGE == 0 ? 0 : 1);
        System.out.println(i);
        return String.valueOf(i);
    }

    public String getXx(String id) {
        object.clear();
        object.put("data", mapper.selectById(id));
        return object.toJSONString();
    }

    public String update(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String id = jsonObject.getString("id");
        String info = jsonObject.getString("info");
        String data = jsonObject.getString("data");
        int update = mapper.update(id, info, data);
        return String.valueOf(update);
    }

    public String del(String s) {
        JSONArray strings = JSONObject.parseObject(s).getJSONArray("strings");
        int size = strings.size();
        for (int i = 0; i < size; i++) {
            mapper.deleteById(strings.getString(i));
        }
        return "1";
    }

    public String search(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String info = jsonObject.getString("info");
        String data = jsonObject.getString("data");
        object.clear();
        object.put("data", mapper.search(info, data));
        return object.toJSONString();
    }
}
