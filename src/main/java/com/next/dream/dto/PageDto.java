package com.next.dream.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：〈分页〉
 *
 * @author liyaohua
 * create on 2018/4/12
 * @version 1.0
 */
@Data
public class PageDto implements Serializable{

    private static final long serialVersionUID = 2736177227994514092L;
    private Integer no;//页号
    private Integer size;//页长
    private boolean firstPage;//是否是首页
    private boolean lastPage;//是否是末页
    private String condition;//排序条件
    private String rowCount;//总行数
    private Integer offset;//起始点
    private Integer pageCount;//页数

}
