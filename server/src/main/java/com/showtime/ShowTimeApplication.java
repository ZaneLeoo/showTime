package com.showtime;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.showtime.mapper")
public class ShowTimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShowTimeApplication.class, args);
    }
}
