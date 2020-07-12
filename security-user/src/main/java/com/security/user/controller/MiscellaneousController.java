package com.security.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 各种前端请求测试
 * @author: stars
 * @create: 2020年 07月 08日 10:47
 **/
@RestController
public class MiscellaneousController {

    @GetMapping("/list")
    public List findData(){
        List<String> list = new ArrayList<>();
        list.add("双肩包");
        list.add("小皮包包");
        list.add("小宝宝");
        list.add("大宝宝");
        return list;
    }
}
