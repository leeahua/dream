package com.next.dream.service;

import com.next.dream.domains.Article;
import com.next.dream.dto.ArticleDto;
import com.next.dream.vo.ResultVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 描述：处理文章信息
 * @author liyaohua
 * @since 1.0.0
 */
public interface ArticleService {


    Page<Article> findByAuthorId(ArticleDto articleDto);

    List<Article> findByCateIds(String cateId);

    Article save(ArticleDto articleDto);

    Article findDetailByAuthorId(ArticleDto articleDto);

    ResultVO delete(Integer id);
    
    Article findById(Integer id);

    ResultVO findBestList();
}
