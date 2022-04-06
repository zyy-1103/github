package com.road.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPatch;
import com.road.bean.Dljcxx;
import com.road.bean.Parse;
import com.road.mapper.DljcxxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Service
public class DljcxxService {
    @Autowired
    DljcxxMapper mapper;
    JSONObject object = new JSONObject();

    public String getData(String curr, HttpServletRequest request) {
        curr = JSONObject.parseObject(curr).getString("curr");
        String id = request.getSession().getAttribute("id").toString();
        if (curr.equals("1")) {
            object.clear();
            object.put("data", mapper.getSquadron(Integer.parseInt(id)));
        }else {
            object.put("data", mapper.getBrigade(Integer.parseInt(id)));
        }
        return object.toJSONString();
    }

    public String getDljcxxXx(String id) {
        object.clear();
        Dljcxx dljcxx = mapper.selectById(id);
        object.put("data", dljcxx);
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
        String oriData = jsonObject.getString("oriData");
        String s1 = mapper.selectSome(id, info);
        if (!oriData.equals(s1)) {
            return "更新数据时发生修改,请刷新确定是否仍要修改此数据";
        }
        return String.valueOf(mapper.update(id, info, data, oriData));
    }

    public String search(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String info = jsonObject.getString("info");
        String data = jsonObject.getString("data");
        object.put("data", mapper.search(info, data));
        System.out.println(object.toJSONString());
        return object.toJSONString();
    }

    public String touch(Parse dljcxx) {
        Dljcxx dljcxx1 = dljcxx.getDljcxx();
        mapper.insert(dljcxx1);
        return "1";
    }
}
