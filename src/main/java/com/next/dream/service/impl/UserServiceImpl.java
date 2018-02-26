package com.next.dream.service.impl;

import com.next.dream.Repository.UserRepository;
import com.next.dream.domains.User;
import com.next.dream.dto.UserDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.enums.UserStatusEnum;
import com.next.dream.service.UserService;
import com.next.dream.utils.*;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 描述：〈〉
 *
 * @author liyaohua
 * @create 2018/2/22
 * @since 1.0.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultVO login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return ResultVOUtil.failed(ResultEnum.USER_NOT_EXISTS);
        }
        if(user.getStatus() == UserStatusEnum.ILLEGAL.getCode()){
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
        user.setStatus(UserStatusEnum.USER_UN_ACTIVICATE.getCode());
        user.setCreateTime(new Date());
        user.setPassword(EnAndDecryptUtils.md5Encrypt(user.getPassword()));
        user.setExpireTime(DateTimeUtil.addDays(new Date(),1));
        userDto.setId(user.getId());
        userDto.setStatus(user.getStatus());
        userDto.setPassword(null);
        //发送邮件
        String code = KeyUtil.randomCode();
        sendRegisterEmail(user.getEmail(),code);
        user.setSalt(code);
        userRepository.save(user);
        return ResultVOUtil.success(userDto);
    }

    @Override
    public ResultVO checkCode(String username, String activateCode) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return ResultVOUtil.failed(ResultEnum.USER_NOT_EXISTS);
        }
        if(user.getStatus().equals(UserStatusEnum.USER_NORMAL)){
            return ResultVOUtil.failed(ResultEnum.USER_ACTIVICATE_ALREADY);
        }
        int k = 0;
        k = DateTimeUtil.compareDateTime(new Date(),user.getExpireTime(),DateTimeUtil.FORMAT_DEFAULT_DATE_TIME);
        if(k > 0){
            return ResultVOUtil.failed(ResultEnum.USER_ACTIVICATE_CODE_EXPIRE);
        }
        user.setStatus(UserStatusEnum.USER_NORMAL.getCode());
        user.setUpdateTime(new Date());
        userRepository.save(user);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO checkUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return ResultVOUtil.failed(ResultEnum.USER_EXISTS_ALREADY);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null){
            return ResultVOUtil.failed(ResultEnum.USER_EMAIL_EXISTS_ALREADY);
        }
        return ResultVOUtil.success();
    }

    private void sendRegisterEmail(String email, String code) {
        try {
            SendEmailUtil.sendTextMail(email,code);
        } catch (Exception e) {
            log.error("邮件发送失败!email:{}",email,e);
        }
        log.info("验证邮件发送成功！email:{}",email);
    }
}
