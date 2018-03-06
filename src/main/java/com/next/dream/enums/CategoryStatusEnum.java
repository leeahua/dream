package com.next.dream.enums;

import lombok.Getter;

/**
 * 描述：类别状态
 *
 * @author liyaohua
 * @date 2018/2/12
 * @since 1.0.0
 */
@Getter
public enum CategoryStatusEnum {
    UN_ACTIVITY(0,"未启用"),
    ACTIVITY(1,"已启用"),
    DISABLED(2,"禁用");


    private Integer code;
    private String msg;

    CategoryStatusEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
