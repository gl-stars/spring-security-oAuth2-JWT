package com.security.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author: stars
 * @date 2020年 07月 08日 15:45
 **/
@SpringBootApplication
public class AuthServerApplication {

    /**
     * 启动工程
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class,args);
    }
}
