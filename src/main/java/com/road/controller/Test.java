package com.road.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class Test {

    @PostMapping(value = "/test")
    public void test(HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        FileReader fileReader = new FileReader("E:\\Java\\ssgl\\src\\main\\resources\\static\\test\\a.xls");

        while (fileReader.ready()) {
            writer.write(fileReader.read());
        }
        writer.flush();
    }
}
