package com.next.dream.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

@Data
@Entity
public class Article{
        @Id
        @GeneratedValue
        private Integer id;
        // 标题
        private String title;
        // 关键字
        private String keywords;
        // 所属领域
        private String cateIds;
        // 作者名称
        private String authorName;
        // 
        private Integer authorId;
        // 文章内容
        private String content;
        // 文章状态
        private Integer status;
        private Date createTime;
        private Date updateTime;
        // 文章概要
        private String summary;


}
