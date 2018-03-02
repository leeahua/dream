package com.next.dream.service.impl;

import com.next.dream.Repository.ArticleRepository;
import com.next.dream.domains.Article;
import com.next.dream.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：〈文章列表〉
 *
 * @author liyaohua
 * create on 2018/3/2
 * @version 1.0
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findByAuthorId(Integer authorId) {
        return articleRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Article> findByCateIds(String cateId) {
        return articleRepository.findByCateIds(cateId);
    }
}
