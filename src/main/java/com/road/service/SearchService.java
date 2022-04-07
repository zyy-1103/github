package com.road.service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.road.bean.SearchResult;
import com.road.mapper.SearchMapper;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SearchService {
    @Autowired
    SearchMapper mapper;
    @Autowired
    @Qualifier("dljcxx")
    HashMap<String, String> hashMap;
    @Autowired
    RedisAsyncCommands<String, String> commands;
    @Autowired
    @Qualifier("file")
    HashMap<String,String> fileMap;
    @Autowired
    ThreadPoolExecutor executor;
    @Autowired
    SpringProducer producer;
    String path = "/var/lib/mysql-files/";

    public void download(String s, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        response.setHeader("content-type", "application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        executor.submit(()->{
            FileReader fileReader = null;
            FileOutputStream fileOutputStream = null;
            StreamGobbler streamGobbler = null;
            PrintWriter writer = response.getWriter();
            try {
                JSONObject object = JSONObject.parseObject(s);
                String sql1 = object.getString("sql1");
                String sql2 = object.getString("sql2");
                String s1 = fileMap.get(sql1 + sql2);
                if (s1 != null) {
                    fileReader = new FileReader(s1);
                    commands.expire(sql1 + sql2, 60);
                }else{
                    String fn = String.valueOf(System.currentTimeMillis());
                    String fileName=path + fn + ".xls";
                    String winFileName = "C:\\" + fn + ".xls";
                    String sql = sql1 + " into outfile \""+fileName+ "\" " + sql2;
                    fileMap.put(sql1 + sql2, winFileName);
                    //缓存
                    commands.setex(sql1 + sql2, 60, "0");
//                    System.out.println("进行缓存");
//                    commands.set(sql1 + sql2, "0");
//                    commands.set("test", "1");
                    mapper.selectAll(sql);

                    Connection connection = new Connection("39.105.175.79", 22);
                    connection.connect();
                    boolean root = connection.authenticateWithPassword("root", "Tf8364334@");
                    Session session = connection.openSession();
                    session.execCommand("cat "+fileName);
                    streamGobbler = new StreamGobbler(session.getStdout());
                    File file = new File(winFileName);
                    fileOutputStream = new FileOutputStream(file);
                    while (true) {
                        int read = streamGobbler.read();
                        if(read==-1)
                            break;
                        fileOutputStream.write(read);
                    }
                    producer.sendMessage("DelTopic", fileName);
                    fileReader = new FileReader(winFileName);
                    fileOutputStream.close();
                }
                while (fileReader.ready()) {
                    writer.write(fileReader.read());
                }
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                writer.close();
                if (streamGobbler != null) {
                    streamGobbler.close();
                }
            }
            return 1;
        }).get();       //使用get会阻塞，不然用户收不到返回的文件

    }


    public String getAll(String s) throws ExecutionException, InterruptedException {
        return executor.submit(()->{
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
            String sql1=builder.toString();
            int len=sql1.length();
            builder.append(" from dljcxx ");
            builder.append(" group by (").append(grade).append(")");
            String sql2 = builder.substring(len);
            System.out.println(builder.toString());
            List<SearchResult> searchResults = mapper.selectAll(builder.toString());
            object.clear();
            object.put("sql1", sql1);
            object.put("sql2", sql2);
            object.put("data", searchResults);
            return object.toJSONString();
        }).get();
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
        String sql1 = builder.toString();
        int len=sql1.length();
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
        String sql2 = builder.substring(len);
        List<SearchResult> searchResults = mapper.selectAll(builder.toString());
        object.clear();
        object.put("data", searchResults);
        object.put("sql1", sql1);
        object.put("sql2", sql2);
        return object.toJSONString();
    }
}
