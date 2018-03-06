package com.next.dream.service.impl;

import com.next.dream.domains.Article;
import com.next.dream.dto.ArticleDto;
import com.next.dream.enums.ArticleStatusEnum;
import com.next.dream.service.ArticleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {


    @Autowired
    private ArticleService articleService;

    @Test
    public void findByAuthorId() {
        Assert.assertNotNull(articleService.findByAuthorId(1));
    }

    @Test
    public void findByCateIds() {
        Assert.assertNotNull(articleService.findByCateIds("1"));
    }

    @Test
    public void save() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setToken("1234");
        articleDto.setTitle("文章标题");
        articleDto.setContent("文章内容");
        articleDto.setKeywords("test");
        articleDto.setCateIds("2");
        articleDto.setStatus(ArticleStatusEnum.UNPUBLISH.getCode());
        Article article = articleService.save(articleDto);
        Assert.assertNotNull(article.getId());
    }

    @Test
    public void delete() {
        Integer articleId = 2;
        articleService.delete(articleId);
        Assert.assertNull(articleService.findById(articleId));
    }
}