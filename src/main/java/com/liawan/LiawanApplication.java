package com.liawan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// @ComponentScan("com.liawan") //@SpringBootApplication 已自动加载这个@ComponentScan 注解
@MapperScan("com.liawan.mapper")
@EnableTransactionManagement
@EnableCaching
public class LiawanApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiawanApplication.class, args);
    }

}
