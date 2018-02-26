package com.next.dream.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 描述：〈用户信息传输类〉
 *
 * @author liyaohua
 * create on 2018/2/23
 * @version 1.0
 */
@Data
public class UserDto {
    private Integer id;
    // 用户名
    @NotNull
    private String username;
    // 密码
    @NotNull
    private String password;
    // 状态
    private Integer status;
    // 邮箱
    private String email;
}
