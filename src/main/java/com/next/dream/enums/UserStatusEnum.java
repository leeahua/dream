package com.next.dream.enums;

import lombok.Getter;

/**
 * 描述：用户状态
 *
 * @author liyaohua
 * @create 2018/2/12
 * @since 1.0.0
 */
@Getter
public enum UserStatusEnum {
    USER_UN_ACTIVICATE(0,"未激活用户"),
    USER_NORMAL(1,"激活用户"),
    ILLEGAL(1,"非法用户");
    private Integer code;
    private String msg;
    UserStatusEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
