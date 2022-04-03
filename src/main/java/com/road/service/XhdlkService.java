package com.road.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.BigFamily;
import com.road.bean.Xhdlk;
import com.road.mapper.XhdlkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XhdlkService {
    @Autowired
    XhdlkMapper mapper;
    JSONObject object = new JSONObject();

    /**
     *
     * @param tableName json格式
     */
    public String getData(String ssdl, String tableName) {
        tableName = JSONObject.parseObject(tableName).getString("tableName");
        object.clear();
        object.put("data", mapper.selectByTableName(tableName, ssdl));
        return object.toJSONString();
    }

    public String getXhdlkXx(String id,String tableName) {
        BigFamily bigFamily = mapper.selectById(id, tableName);
        object.clear();
        object.put("data", bigFamily);
        return object.toJSONString();
    }

    public String del(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String tableName = jsonObject.getString("tableName");
        JSONArray strings = jsonObject.getJSONArray("strings");
        int size = strings.size();
        for (int i = 0; i < size; i++) {
            mapper.deleteByTableName(tableName, strings.getString(i));
        }
        return "1";
    }

    public String update(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String id = jsonObject.getString("id");
        String info = jsonObject.getString("info");
        String data = jsonObject.getString("data");
        String tableName = jsonObject.getString("tableName");
        mapper.updateByTableName(tableName, info, data, id);
        return "1";
    }
}
