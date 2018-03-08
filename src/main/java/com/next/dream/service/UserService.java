package com.next.dream.service;

import com.next.dream.dto.UserDto;
import com.next.dream.vo.ResultVO;

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
    ResultVO login(String username, String password,String token);

    /**
     * 注册处理
     * @param user
     * @return 结果集
     * */
    ResultVO save(UserDto user);

    /**
     * 注册处理
     * @param user
     * @return 结果集
     * */
    ResultVO update(UserDto user);

    /**
     * @Description: <br>
     *  激活用户
     * @return ResultVO 结果集
     * @author liyaohua
     * Created On 2018/2/26 上午10:37
     */
    ResultVO checkCode(String username, String activateCode);

    /**
     * @Description: <br>
     *  校验用户名
     * @return ResultVO  结果集
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/2/26 下午3:38
     */
    ResultVO checkUsername(String username);
    /**
     * @Description: <br>
     *  校验邮箱是否存在
     * @return 结果集
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/2/26 下午3:45
     */
    ResultVO checkEmail(String email);
}
