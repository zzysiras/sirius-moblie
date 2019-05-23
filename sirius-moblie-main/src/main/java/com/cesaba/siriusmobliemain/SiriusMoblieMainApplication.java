package com.cesaba.siriusmobliemain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@MapperScan("com.cesaba.siriusmobliemain.mapper")
public class SiriusMoblieMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiriusMoblieMainApplication.class, args);
    }
}

