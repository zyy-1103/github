package com.road.test;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.road.utils.RandomUtil;
import com.road.utils.Roles;
import org.assertj.core.util.Files;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\data.txt")));
        String s;
        while ((s = reader.readLine()) != null) {
            String[] s1 = s.split("\t");
            System.out.println(s1.length);
            for (String i : s1) {
                System.out.println(i);
            }
        }
    }
}
