package com.next.dream.service;

import com.next.dream.vo.ResultVO;

import java.util.Map;

/**
 * 描述：〈用户业务逻辑处理〉
 *
 * @author liyaohua
 * @create 2018/2/22
 * @since 1.0.0
 */
public interface UserService {

    /**
     * 登陆处理
     * @param username
     * @param password
     * @return
     * */
    public ResultVO login(String username, String password);
}
