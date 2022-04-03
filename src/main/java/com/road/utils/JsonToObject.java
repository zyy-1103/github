package com.road.utils;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.road.bean.Dljcxx;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class JsonToObject<T> {
    public static <T> T getObj(String json, Class<T> c) throws Exception {
        JSONObject object = JSONObject.parseObject(json);
        T t = c.newInstance();

        for (Field declaredField : c.getDeclaredFields()) {
            String name = declaredField.getName();
            declaredField.setAccessible(true);
            if (declaredField.getType().isPrimitive()) {
                continue;
            }
            Object o = declaredField.getType().newInstance();
            declaredField.getType().getMethod("valueOf").invoke(o, object.getString(name));
            Integer.valueOf(object.getString(name));
        }
        return t;
    }

    public static void main(String[] args) throws Exception {
        JSONObject object = new JSONObject();
        object.put("dlm", "nihao");
        object.put("id", 1);
        Dljcxx obj = getObj(object.toJSONString(), Dljcxx.class);
        System.out.println(obj.getDlm());
    }
}
