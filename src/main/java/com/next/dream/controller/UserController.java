package com.next.dream.controller;

import com.next.dream.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 描述：〈用户控制器〉
 *
 * @author liyaohua
 * @create 2018/2/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Object login(@RequestBody String username, @RequestBody  String password){
        log.info("【登陆处理】 接收参数:{}",username);
        return userService.login(username,password);
    }


}
