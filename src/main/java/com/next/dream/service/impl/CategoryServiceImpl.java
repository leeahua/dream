package com.next.dream.service.impl;

import com.next.dream.domains.Category;
import com.next.dream.enums.CategoryStatusEnum;
import com.next.dream.enums.ResultEnum;
import com.next.dream.repository.CategoryRepository;
import com.next.dream.service.CategoryService;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * 描述：〈〉
 *
 * @author liyaohua
 * create on 2018/3/6
 * @version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByStatus(Integer status) {
        return categoryRepository.findByStatus(status);
    }

    @Override
    public ResultVO disableCategory(Integer categoryId) {
        Category category = categoryRepository.findOne(categoryId);
        if(category == null){
            return ResultVOUtil.failed(ResultEnum.CATEGORY_NOT_EXISTS);
        }
        category.setStatus(CategoryStatusEnum.DISABLED.getCode());
        category.setUpdateTime(new Date());
        categoryRepository.save(category);
        return ResultVOUtil.success();
    }


}
