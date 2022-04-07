package com.road.service;

import com.road.bean.*;
import com.road.mapper.*;
import com.road.utils.RandomUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class DljcxxServiceTest {

    @Autowired
    DljcxxService service;

    @Autowired
    XhdlkMapper xhdlkMapper;

    @Autowired
    BzpMapper bzpMapper;

    @Autowired
    JszMapper jszMapper;
    @Autowired
    LnbwMapper lnbwMapper;
    @Test
    void xhdlk(){
        int index = 300;
        Lnbw lnbw = new Lnbw(0, "1", 0, 100, "10", "10", "1", "1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "0", 1, "1.1.1.1");
        Bzp bzp = new Bzp(1, 1, "东", "内容", "吉大", "1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "1", 1, "1.1.1.1");
        Jsz jsz = new Jsz(0, 0, LocalDate.now(), LocalDate.now(), LocalDate.now(), "0", 1, "1.1.1.1", 10);
        for (int i = 1; i < 50; i++) {
            for (int j = 0; j < 50;j++) {
                lnbw.setId(index++);
                lnbw.setSsdl(i);
                lnbwMapper.insert(lnbw);
            }
        }
    }

    @Test
    void touch() throws IOException {
        Parse parse = new Parse();
        String[] ints = {"1", "21", "22"};
        Dljcxx dljcxx = new Dljcxx(0, 110.0, 29.0, 110.0, 29.5, "1", "2", "0", "1", "1", 100, 100, "备注", "结构", "备注", "分布", "分布", "方向",
                "吉大", "Sky", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, LocalDate.now(), LocalDate.now(), LocalDate.now(), "0", 1, "1.1.1.1", "1", "1", "备注", 1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\data.txt")));
        String s = reader.readLine();
        int f=0;
        int index=30;
        while (s != null) {
            String[] s1 = s.split("\t");
            for (String i : s1) {
                dljcxx.setSszd(ints[f++]);
                dljcxx.setId(index++);
                f%=3;
                dljcxx.setDlm(i);
                dljcxx.setBzpcount(RandomUtil.getInt());
                dljcxx.setJshcdcount(RandomUtil.getInt());
                dljcxx.setGlsscount(RandomUtil.getInt());
                dljcxx.setIsglsscount(RandomUtil.getInt());
                dljcxx.setIsglsscount(RandomUtil.getInt());
                dljcxx.setGjzdcount(RandomUtil.getInt());
                dljcxx.setGjzdcount(RandomUtil.getInt());
                dljcxx.setFdkzxlcount(RandomUtil.getInt());
                dljcxx.setXhdlkcount(RandomUtil.getInt());
                dljcxx.setDlqcount(RandomUtil.getInt());
                dljcxx.setLsydbzcount(RandomUtil.getInt());
                dljcxx.setJzwcrkcount(RandomUtil.getInt());
                dljcxx.setRxhdxcount(RandomUtil.getInt());
                dljcxx.setTqlbcount(RandomUtil.getInt());
                dljcxx.setLnbwcount(RandomUtil.getInt());
                dljcxx.setQtjtsscou(RandomUtil.getInt());
                parse.setDljcxx(dljcxx);
                service.touch(parse);
            }
            s = reader.readLine();
        }
    }
}