package com.chzu.ice.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mason
 */
@SpringBootApplication
@MapperScan("com.chzu.ice.chat.mapper")
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
        System.out.println("程序运行中...");
    }
}
