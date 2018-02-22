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
    NORMAL(0,"普通用户");
    private Integer code;
    private String msg;
    UserStatusEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
