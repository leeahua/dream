package com.next.dream.service.impl;

import com.next.dream.Repository.UserRepository;
import com.next.dream.domains.User;
import com.next.dream.dto.UserDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.enums.UserStatusEnum;
import com.next.dream.service.UserService;
import com.next.dream.utils.EnAndDecryptUtils;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        if(user.getStatus()== UserStatusEnum.ILLEGAL.getCode()){
            return ResultVOUtil.failed(ResultEnum.USER_ILLEGAL);
        }
        if(!user.getPassword().equals(EnAndDecryptUtils.md5Encrypt(password))){
            return ResultVOUtil.failed(ResultEnum.USER_PASSWORD_ERROR);
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setPassword(null);
        return ResultVOUtil.success(userDto);
    }

    @Override
    @Transactional
    public ResultVO save(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        //判断该用户是否存在
        User dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser!=null){
            return ResultVOUtil.failed(ResultEnum.USER_EXISTS_ALREADY);
        }
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        user.setCreateTime(new Date());
        user.setPassword(EnAndDecryptUtils.md5Encrypt(user.getPassword()));
        userRepository.save(user);
        userDto.setId(user.getId());
        userDto.setStatus(user.getStatus());
        userDto.setPassword(null);
        return ResultVOUtil.success(userDto);
    }
}
