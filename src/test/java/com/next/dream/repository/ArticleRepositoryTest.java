package com.next.dream.repository;

import com.next.dream.domains.Article;
import com.next.dream.enums.ArticleStatusEnum;
import com.next.dream.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void insert(){
        Article article = new Article();
        article.setAuthorId(1);
        article.setAuthorName("lee");
        article.setCateIds("1");
        article.setContent("this is a test");
        article.setKeywords("test");
        article.setStatus(ArticleStatusEnum.UNPUBLISH.getCode());
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article = articleRepository.save(article);
        Assert.assertNotNull(article);
        System.out.println(JsonUtil.toJson(article));
    }

    @Test
    public void findByAuthorId() {
        List<Article> articles = articleRepository.findByAuthorId(1);
        System.out.println(JsonUtil.toJson(articles));
    }

    @Test
    public void findByCateIds() {
        List<Article> articles = articleRepository.findByCateIds("1");
        System.out.println(JsonUtil.toJson(articles));
    }
}