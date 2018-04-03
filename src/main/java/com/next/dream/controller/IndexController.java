package com.next.dream.controller;


import com.next.dream.utils.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 * */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/data")
    public Object data(HttpServletRequest request){
        Map<String,String> data = new HashMap<>();
        data.put("aaa","1234");
        data.put("bbb","5678");
        System.out.println(request.getSession().getId()+"---"+ JsonUtil.toJson(request.getSession().getAttribute("boot")));
        if(request.getSession().getAttribute("boot")==null) {
            HttpSession session = request.getSession();
            session.setAttribute("boot", data);
        }
        System.out.println(request.getSession().getId()+"---"+ JsonUtil.toJson(request.getSession().getAttribute("boot")));
        return data;
    }

}
