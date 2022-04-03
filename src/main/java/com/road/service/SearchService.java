package com.road.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.SearchResult;
import com.road.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class SearchService {
    @Autowired
    SearchMapper mapper;
    @Autowired
    HashMap<String, String> hashMap;

    File file = new File("C:\\");

    public String download(String sql) {
        return null;
    }


    public String getAll(String s) {
        JSONObject object = JSONObject.parseObject(s);
        String grade = object.getString("grade");
        String name;
        if (grade.equals("sszd")) {
            name = "所属中队";
        }else {
            name = "所属大队";
        }
        StringBuilder builder = new StringBuilder("select '"+name+"' as "+grade);
        hashMap.entrySet().iterator().forEachRemaining(e->{
            builder.append(",'").append(e.getValue()).append("' as ").append(e.getKey());
        });
        builder.append(" union all ");
        builder.append("select ifnull(").append(grade).append(",'UnKnow') as ").append(grade).append(",count(dljcxx.id) as dljcxx");
        hashMap.keySet().iterator().forEachRemaining(e->{
            if (!e.equals("dljcxx")) {
                builder.append(",ifnull(sum(").append(e).append("count),0) as ").append(e);
            }
        });
        builder.append(" from dljcxx ");
        builder.append(" group by (").append(grade).append(")");
        List<SearchResult> searchResults = mapper.selectAll(builder.toString());
        object.clear();
        object.put("data", searchResults);
        return object.toJSONString();
    }

    public String getByCondition(String s) {
        JSONObject object = JSONObject.parseObject(s);
        String grade = object.getString("grade");
        String orgName = object.getString("orgName");
        String dlInfo = object.getString("dlInfo");
        String dlData = object.getString("dlData");
        JSONArray showList = object.getJSONArray("showList");
        JSONArray t = object.getJSONArray("t");
        JSONArray y = object.getJSONArray("y");
        JSONArray d = object.getJSONArray("d");
        StringBuilder builder = new StringBuilder("select '道路名' as dlm ");
        for (int i = 0; i < showList.size(); i++) {
            builder.append(",'").append(hashMap.get(showList.getString(i))).append("' as ").append(showList.getString(i));
        }
        builder.append(" union all select ifnull(dlm,'unknow') as dlm");
        for (int i = 0; i < showList.size(); i++) {
            builder.append(",ifnull(").append(showList.getString(i)).append("count,'unknow') as ").append(showList.getString(i));
        }
        builder.append(" from dljcxx where ");
        if (!dlData.equals("")) {
            builder.append(grade).append("='").append(orgName).append("'");
            builder.append(" and ").append(dlInfo).append("='").append(dlData).append("'");
        }else {
            builder.append(" 1=1 ");
        }
        for (int i = 0; i < t.size(); i++) {
            if (!t.getString(i).equals("")) {
                builder.append(" and ").append(t.getString(i)).append("count").append(y.getString(i)).append("'").append(d.getString(i)).append("'");
            }
        }
        List<SearchResult> searchResults = mapper.selectAll(builder.toString());
        object.clear();
        object.put("data", searchResults);
        System.out.println(builder.toString());
        System.out.println(object.toJSONString());
        return object.toJSONString();
    }
}
