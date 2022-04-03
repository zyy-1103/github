package com.road.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import org.junit.jupiter.api.Test;

public class MPTest {

    @Test
    public void test(){


        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ssgl?serverTimezone=UTC&useSSL=true","root","zyy1103..")
                .globalConfig(e->{
                    e.outputDir("C:\\Users\\ASUS\\Desktop\\Java\\untitled1\\road" + "/src/main/java")
                            .author("sky");
                })
                .packageConfig(e->{
                    e.parent("com.road")
                            .entity("bean")
                            .mapper("mapper")
                            .controller("controller")
                            .service("service");
                })
                .strategyConfig(e->{
                    e.entityBuilder()
                            .enableLombok()
                            .mapperBuilder()
                            .enableMapperAnnotation();
                })
//                .templateEngine(new FreemarkerTemplateEngine())

                .execute();


    }
}
