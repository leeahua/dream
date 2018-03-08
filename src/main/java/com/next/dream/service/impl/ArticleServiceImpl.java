package com.next.dream.service.impl;

import com.next.dream.repository.ArticleRepository;
import com.next.dream.domains.Article;
import com.next.dream.dto.ArticleDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.service.ArticleService;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Article save(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        article.setCreateTime(new Date());
        if(article.getContent().length()<10){
            article.setSummary(article.getContent());
        }else{
            article.setSummary(article.getContent().substring(0,10));
        }

        return articleRepository.save(article);
    }

    @Override
    public ResultVO delete(Integer id) {
        Article article = articleRepository.findOne(id);
        if(article == null){
            return ResultVOUtil.failed(ResultEnum.ARTICLE_NOT_EXISTS);
        }
        articleRepository.delete(article);
        return ResultVOUtil.success();
    }

    @Override
    public Article findById(Integer id) {
        return articleRepository.findOne(id);
    }

    @Override
    public ResultVO findBestList() {
        List<Article> data = articleRepository.findAll();
        return ResultVOUtil.success(data);
    }
}
