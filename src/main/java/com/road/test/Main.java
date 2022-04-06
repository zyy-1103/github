package com.road.test;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.mysql.cj.jdbc.ConnectionImpl;
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
//        Connection connection = new Connection("39.105.175.79", 22);
//        connection.connect();
//        boolean root = connection.authenticateWithPassword("root", "Tf8364334@");
//        System.out.println(root);
//        Session session = connection.openSession();
//        session.execCommand("cat /var/lib/mysql-files/1649234808195.xls");
//        StreamGobbler streamGobbler = new StreamGobbler(session.getStdout());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(streamGobbler));
//        String line;
//        File file = new File("C:\\a.xls");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        while (true) {
//            int s = streamGobbler.read();
//            if(s==-1)
//                break;
//            fileOutputStream.write(s);
//        }


        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.schedule(() -> {

        }, 60, TimeUnit.SECONDS);
        service.submit(()->{
            System.out.println("1");
        }).get();

    }
}
