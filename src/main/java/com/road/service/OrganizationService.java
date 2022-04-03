package com.road.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.Organization;
import com.road.bean.Parse;
import com.road.mapper.OrganizationMapper;
import netscape.javascript.JSObject;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {
    @Autowired
    OrganizationMapper mapper;
    JSONObject object = new JSONObject();

    public String getData() {
        List<Organization> organizations = mapper.selectAll();

        List<Organization> three = organizations.stream().filter(e -> e.getBmjb().equals("科室") || e.getBmjb().equals("中队")).collect(Collectors.toList());
        List<Organization> two = organizations.stream().filter(e -> e.getBmjb().equals("处室") || e.getBmjb().equals("大队")).collect(Collectors.toList());
        List<Organization> one = organizations.stream().filter(e -> e.getBmjb().equals("支队")).collect(Collectors.toList());
        object.clear();
        object.put("one", one);
        object.put("two", two);
        object.put("three", three);
        return object.toJSONString();
    }

    public String getXx(String id) {
        object.clear();
        object.put("data", mapper.selectById(id));
        return object.toJSONString();
    }

    public String del(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String bmmc = jsonObject.getString("bmmc");
        return String.valueOf(mapper.deleteByBmmc(bmmc));
    }

    public String update(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String org = jsonObject.getString("org");
        String info = jsonObject.getString("info");
        String data = jsonObject.getString("data");
        mapper.updateInfo(org, info, data);
        return "1";
    }

    public String touch(Parse parse) {
        Organization organization = parse.getOrganization();
        mapper.insert(organization);
        return "1";
    }
}
