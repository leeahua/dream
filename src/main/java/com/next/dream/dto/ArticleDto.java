package com.next.dream.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 描述：〈文章传输〉
 *
 * @author liyaohua
 * create on 2018/3/5
 * @version 1.0
 */
@Data
public class ArticleDto extends BaseRespDto{
    private Integer id;
    // 标题
    @NotNull
    private String title;
    // 关键字
    @NotNull
    private String keywords;
    // 所属领域
    @NotNull
    private String cateIds;
    // 作者名称
    private String authorName;
    //
    @NotNull
    private Integer authorId;
    // 文章内容
    @NotNull
    private String content;
    // 文章状态
    @NotNull
    private Integer status;
    private Date createTime;
    private Date updateTime;
    // 文章概要
    private String summary;
    private Integer viewCount;
    private Integer commentCount;
}
