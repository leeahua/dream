package com.next.dream.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：〈基础数据传输数据类〉
 *
 * @author liyaohua
 * create on 2018/3/5
 * @version 1.0
 */
@Data
public class BaseRespDto implements Serializable{
    private static final long serialVersionUID = -5341541539495391504L;
    private String token;
}
