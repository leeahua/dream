package com.next.dream.service.impl;

import com.next.dream.domains.User;
import com.next.dream.dto.UserDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.enums.UserStatusEnum;
import com.next.dream.repository.UserRepository;
import com.next.dream.service.EmailService;
import com.next.dream.service.RedisService;
import com.next.dream.service.UserService;
import com.next.dream.utils.*;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 描述：〈〉
 *
 * @author liyaohua
 * @since 1.0.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisService redisService;

    @Override
    public ResultVO login(UserDto userDto) {
        String username = userDto.getUsername();

        //判断是否已经登陆过
        //TODO 需要防止用户重复登陆 造成过多的session
        //判断该用户是否已经登陆过，如果登陆过则用已有的session，否则 新增session

        String token = (String)redisService.get(username);
        if(userDto.getToken()==null) {
            if(token==null){
                log.info("用户登陆,token：{}",token);
                token = KeyUtil.getUUID(true);
            }else{
                log.info("用户已有登陆session,token：{}",token);
                UserDto user = (UserDto) redisService.get(token);
                if(user != null){
                    redisService.set(token,userDto,60, TimeUnit.MINUTES); //默认一个小时
                    redisService.set(userDto.getUsername(),token,59, TimeUnit.MINUTES); //默认一个小时
                    user.setToken(token);
                    return ResultVOUtil.success(user);
                }
            }

        }else{
            token = userDto.getToken();
        }

        User user = userRepository.findByUsername(username);
        if(user == null){
            return ResultVOUtil.failed(ResultEnum.USER_NOT_EXISTS);
        }
        if(user.getStatus() != UserStatusEnum.USER_NORMAL.getCode()){
            return ResultVOUtil.failed(ResultEnum.USER_ILLEGAL);
        }

        if(!user.getPassword().equals(EnAndDecryptUtils.md5Encrypt(userDto.getPassword()))){
            return ResultVOUtil.failed(ResultEnum.USER_PASSWORD_ERROR);
        }
        UserDto result = new UserDto();
        BeanUtils.copyProperties(user,result);
        result.setPassword(null);
        result.setToken(token);
        redisService.set(token,result,60, TimeUnit.MINUTES); //默认一个小时
        redisService.set(username,token,59, TimeUnit.MINUTES);
        log.info("返回前：{}",JsonUtil.toJson(result));
        return ResultVOUtil.success(result);
    }

    @Override
    @Transactional
    public ResultVO save(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        //判断该用户是否存在
        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser != null) {
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
        user.setSalt(code);
        userRepository.save(user);
        sendRegisterEmail(user);
        return ResultVOUtil.success(userDto);
    }

    /**
     * 更新用户
     * @param user
     * @return ResultVO
     * @author liyaohua
     * Created On 2018/3/8 下午4:51
     */
    @Override
    public ResultVO update(UserDto user) {
        User dbUser = userRepository.findOne(user.getId());
        if (dbUser == null) {
            return ResultVOUtil.failed(ResultEnum.USER_NOT_EXISTS);
        }
        if(user.getUsername()!=null && !dbUser.getUsername().equals(user.getUsername())){
            dbUser.setUsername(user.getUsername());
        }
        userRepository.save(dbUser);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO checkCode(String username, String activateCode) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return ResultVOUtil.failed(ResultEnum.USER_NOT_EXISTS);
        }
        if(user.getStatus() == UserStatusEnum.USER_NORMAL.getCode()){
            return ResultVOUtil.failed(ResultEnum.USER_ACTIVICATE_ALREADY);
        }
        if(!EnAndDecryptUtils.md5Encrypt(username+":"+user.getSalt()).equals(activateCode)){
            return ResultVOUtil.failed(ResultEnum.USER_ACTIVICATE_CODE_UNMATCH);
        }
        int k = DateTimeUtil.compareDateTime(new Date(),user.getExpireTime(),DateTimeUtil.FORMAT_DEFAULT_DATE_TIME);
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

    private void sendRegisterEmail(User user) {
        try {
            String checkCode = EnAndDecryptUtils.md5Encrypt(user.getUsername()+":"+user.getSalt());
            String urlLink = emailService.getRegisterUrlLink();
            urlLink = urlLink.replace("$username$",user.getUsername()).replace("$code$",checkCode);
            String htmlContent = SendEmailUtil.emaliTemplate();
            htmlContent = htmlContent.replace("$username$",user.getUsername());
            htmlContent = htmlContent.replace("$urllink$",urlLink);
            emailService.sendHtmlMail(user.getEmail(),"账号激活通知邮件",htmlContent);
        } catch (Exception e) {
            log.error("邮件发送失败!email:{}",user.getEmail(),e);
        }
        log.info("验证邮件发送成功！email:{}",user.getEmail());
    }



}
