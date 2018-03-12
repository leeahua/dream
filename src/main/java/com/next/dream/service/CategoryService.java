package com.next.dream.service;

import com.next.dream.domains.Category;
import com.next.dream.dto.CategoryDto;
import com.next.dream.vo.ResultVO;

import java.util.List;

/**
 * 描述：〈类型〉
 *
 * @author liyaohua
 * create on 2018/3/6
 * @version 1.0
 */

public interface CategoryService {

    List<Category> findAll();

    List<Category> findByStatus(Integer status);

    ResultVO disableCategory(Integer categoryId);

    ResultVO addOrUpdate(CategoryDto categoryDto);

    void delete(CategoryDto categoryDto);


}
