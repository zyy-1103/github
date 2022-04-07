package com.road.controller;

import com.road.service.SearchProducer;
import com.road.service.SearchService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/search/")
public class SearchController {
//    @Autowired
//    SearchProducer producer;
    @Autowired
    SearchService service;
//
//    @GetMapping(value = "test")
//    public String sendMsg(@Param(value = "msg") String msg) {
//        producer.sendMsg("TestTopic", msg);
//        return "信息发送完成";
//    }

    @PostMapping(value = "searchAll")
    public String getAll(@RequestBody String s) throws ExecutionException, InterruptedException {
        return service.getAll(s);
    }

    @PostMapping(value = "searchCon")
    public String getCon(@RequestBody String s) {
        return service.getByCondition(s);
    }

    @PostMapping(value = "download")
    public void download(@RequestBody String s, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        service.download(s, response);
    }
}
