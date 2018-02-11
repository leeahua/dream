package com.next.dream.enums;

import lombok.Getter;

/**
 * 描述：文章状态
 *
 * @author liyaohua
 * @create 2018/2/11
 * @since 1.0.0
 */
@Getter
public enum ArticleStatusEnum {
    UNPUBLISH(0,"未发布"),
    DELETE(2,"删除"),
    PUBLISH(1,"已发布");

    private Integer code;
    private String msg;
    ArticleStatusEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
