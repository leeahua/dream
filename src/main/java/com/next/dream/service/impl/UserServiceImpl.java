package com.next.dream.service.impl;

import com.next.dream.Repository.UserRepository;
import com.next.dream.domains.User;
import com.next.dream.enums.ResultEnum;
import com.next.dream.service.UserService;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 描述：〈〉
 *
 * @author liyaohua
 * @create 2018/2/22
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultVO login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return ResultVOUtil.failed(ResultEnum.USER_NOT_EXISTS);
        }
        return null;
    }
}
