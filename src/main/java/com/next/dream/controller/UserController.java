package com.next.dream.controller;

import com.next.dream.dto.UserDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.service.UserService;
import com.next.dream.utils.JsonUtil;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 描述：〈用户控制器〉
 *
 * @author liyaohua
 * @create 2018/2/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        log.info("【登陆】 接收参数:{}",username);
        return userService.login(username,password);
    }

    @PostMapping("/register")
    public Object register(@RequestBody  @Valid UserDto user, BindingResult result){
        log.info("【注册】 接收参数:{}", JsonUtil.toJson(user));
        if(result.hasErrors()){
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultVOUtil.getMsg(result));
        }
        return userService.save(user);
    }


}
