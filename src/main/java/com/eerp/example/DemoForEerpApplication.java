package com.eerp.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.eerp.example*.mapper")
public class DemoForEerpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoForEerpApplication.class, args);
    }

}
