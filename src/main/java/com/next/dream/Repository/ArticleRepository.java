package com.next.dream.Repository;

import com.next.dream.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述：〈文章〉
 *
 * @author liyaohua
 * @create 2018/2/11
 * @since 1.0.0
 */
public interface ArticleRepository extends JpaRepository<Article,Integer>{
}
