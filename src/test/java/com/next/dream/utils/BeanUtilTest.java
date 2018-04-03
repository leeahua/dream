package com.next.dream.utils;

import com.next.dream.domains.User;
import com.next.dream.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述：〈beancopy测试〉
 *
 * @author liyaohua
 * create on 2018/3/8
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanUtilTest {

    @Test
    public void test(){
        UserDto userDto = new UserDto();
        userDto.setToken("1111");
        userDto.setPassword("2222");
        User user = new User();
        user.setStatus(2);
        user.setPassword("333");
        System.out.println(JsonUtil.toJson(user));
        BeanUtils.copyProperties(userDto,user);
        System.out.println(JsonUtil.toJson(user));
    }



}
