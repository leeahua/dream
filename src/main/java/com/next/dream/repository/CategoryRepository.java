package com.next.dream.repository;

import com.next.dream.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：〈类别〉
 *
 * @author liyaohua
 * @create 2018/2/12
 * @since 1.0.0
 */
public interface CategoryRepository extends JpaRepository<Category,Integer>{

    List<Category> findAll();

    List<Category> findByStatus(Integer status);
}
