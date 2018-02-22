package com.next.dream.Repository;

import com.next.dream.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 描述：〈类别〉
 *
 * @author liyaohua
 * @create 2018/2/12
 * @since 1.0.0
 */
public interface CategoryRepository extends JpaRepository<Category,Integer>{


}
