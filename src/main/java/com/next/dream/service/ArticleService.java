package com.next.dream.service;

import com.next.dream.domains.Article;

import java.util.List;

/**
 * 描述：处理文章信息
 * @author liyaohua
 * @since 1.0.0
 */
public interface ArticleService {

    List<Article> findByAuthorId(Integer authorId);

    List<Article> findByCateIds(String cateId);
}
