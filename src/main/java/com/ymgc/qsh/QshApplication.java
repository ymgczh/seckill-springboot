package com.ymgc.qsh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ymgc.qsh.mapper")
public class QshApplication {

    public static void main(String[] args) {
        SpringApplication.run(QshApplication.class, args);
    }

}
