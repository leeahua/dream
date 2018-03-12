package com.next.dream.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 描述：类别数据传输类
 *
 * @author liyaohua
 * create on 2018/3/12
 * @version 1.0
 */
@Data
public class CategoryDto extends BaseDto implements Serializable {

    private Integer id;
    // 类别名称
    @NotNull
    private String name;
    // 状态（0 不可用，1可用）
    private Integer status;

}
