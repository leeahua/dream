package com.next.dream.enums;

import lombok.Getter;

/**
 * 描述：商品状态信息
 *
 * @author liyaohua
 * @create 2018/1/31
 * @since 1.0.0
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
