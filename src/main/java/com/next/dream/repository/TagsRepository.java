package com.next.dream.repository;

import com.next.dream.domains.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：个人类别标签
 *
 * @author liyaohua
 * @create 2018/3/12
 * @since 1.0.0
 */
public interface TagsRepository extends JpaRepository<Tags,Integer> {

    List<Tags> findByStatus(Integer status);


}
