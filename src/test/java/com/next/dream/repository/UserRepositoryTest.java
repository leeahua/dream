package com.next.dream.repository;

import com.next.dream.Repository.UserRepository;
import com.next.dream.domains.User;
import com.next.dream.enums.UserStatusEnum;
import com.next.dream.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insert(){
        User user = new User();
        user.setEmail("leeahuamsg@163.com");
        user.setUsername("leeahuamsg@163.com");

        user.setSalt("123");
        user.setPassword(MD5U);
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        user.setRoleIds("1");
        user.setCreateTime(new Date());
        user = userRepository.save(user);
        Assert.assertNotNull(user);
        System.out.println(JsonUtil.toJson(user));
    }

    @Test
    public void delete(){
        User user = new User();
        user.setId(1);
        userRepository.delete(user);

    }


}