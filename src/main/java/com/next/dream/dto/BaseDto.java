package com.next.dream.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：〈〉
 *
 * @author liyaohua
 * create on 2018/3/8
 * @version 1.0
 */
@Data
public class BaseDto implements Serializable {

    private static final long serialVersionUID = -4201684362407432547L;
    private String token;
}
