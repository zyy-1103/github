package com.road;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class RoadApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoadApplication.class, args);
    }

}
