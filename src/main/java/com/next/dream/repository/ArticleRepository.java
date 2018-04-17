package com.next.dream.repository;

import com.next.dream.domains.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：〈文章〉
 *
 * @author liyaohua
 * @create 2018/2/11
 * @since 1.0.0
 */
public interface ArticleRepository extends JpaRepository<Article,Integer>{

    List<Article> findByAuthorId(Integer authorId);

    List<Article> findByCateIds(String cateId);

    Article findByAuthorIdAndId(Integer authorId,Integer id);

    Page<Article> findByAuthorIdAndStatus(Integer authorId , Integer status, Pageable pageable);


}
