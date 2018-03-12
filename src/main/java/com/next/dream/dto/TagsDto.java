package com.next.dream.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户自定义标签
 * @author liyaohua
 * create on 2018/3/12
 * @version 1.0
 */
@Data
public class TagsDto extends BaseDto implements Serializable{

    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private String tagName;

    private Integer status;

    private Date updateTime;
}
