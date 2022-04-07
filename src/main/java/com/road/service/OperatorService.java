package com.road.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.Operator;
import com.road.bean.Operatorrole;
import com.road.bean.Parse;
import com.road.bean.Role;
import com.road.mapper.JfoduleMapper;
import com.road.mapper.OperatorMapper;
import com.road.mapper.OperatorroleMapper;
import com.road.mapper.RoleMapper;
import com.road.utils.Roles;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService {
    @Autowired
    private OperatorMapper mapper;
    @Autowired
    private RoleMapper mapper1;
    @Autowired
    private JfoduleMapper mapper2;
    @Autowired
    OperatorroleMapper operatorroleMapper;

    public Operator getOperatorByName(String name) {
        System.out.println(name);
        int id = mapper.getIdByName(name);
        Operator operator = mapper.selectById(id);
        List<Role> roles = mapper1.getRolesByOperatorId(id);
        roles.iterator().forEachRemaining(e -> {
            e.setJfodules(mapper2.getModuleByRoleId(e.getId()));
        });
        operator.setRoles(roles);
        return operator;
    }

    JSONObject object = new JSONObject();
    public String getData(){
        object.clear();
        object.put("data", mapper.selectAll());
        return object.toJSONString();
    }

    public String getXx(String id) {
        object.clear();
        object.put("data", mapper.getById(id));
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
        Operator operator = parse.getOperator();
        mapper.insert(operator);
        for (Roles roles : Roles.values()) {
            if (roles.name().equals(operator.getQldj())) {
                Operatorrole operatorrole=new Operatorrole();
                operatorrole.setCzyid(mapper.selectId());
                operatorrole.setJsid(roles.ordinal() + 1);
                operatorrole.setId(0);
                operatorroleMapper.insert(operatorrole);
            }
        }
        return "1";
    }
}
