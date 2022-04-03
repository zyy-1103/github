package com.road.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.Datadictionaryitem;
import com.road.bean.Parse;
import com.road.mapper.DatadictionaryitemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataDicItemService {
    @Autowired
    DatadictionaryitemMapper mapper;
    JSONObject object = new JSONObject();

    public String getData(){
        object.clear();
        object.put("data", mapper.selectAll());
        return object.toJSONString();
    }

    public String touch(Parse parse) {
        Datadictionaryitem datadictionaryitem = parse.getDatadictionaryitem();
        mapper.insert(datadictionaryitem);
        return "1";
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
        object.put("data", mapper.selectById(id));
        return object.toJSONString();
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
