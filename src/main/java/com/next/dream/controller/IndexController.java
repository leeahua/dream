package com.next.dream.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 * */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/data")
    public Object data(){
        Map<String,String> data = new HashMap<>();
        data.put("aaa","1234");
        data.put("bbb","5678");
        return data;
    }

}
