package com.next.dream.service;

import com.next.dream.dto.UserDto;
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
     * @return ResultVO 结果集
     * */
    ResultVO login(String username, String password);

    /**
     * 注册处理
     * @param user
     * @return 结果集
     * */
    ResultVO save(UserDto user);

    /**
     * @Description: <br>
     *  激活用户
     * @return ResultVO 结果集
     * @author liyaohua
     * Created On 2018/2/26 上午10:37
     */
    ResultVO checkCode(String username, String activateCode);
}
