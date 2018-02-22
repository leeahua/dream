package com.next.dream.repository;

import com.next.dream.Repository.CategoryRepository;
import com.next.dream.domains.Category;
import com.next.dream.enums.CategoryStatusEnum;
import com.next.dream.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    public void save(){
        Category category = new Category();
        category.setName("测试");
        category.setParentId(-1);
        category.setParentIds("-1");
        category.setStatus(CategoryStatusEnum.ACTIVITY.getCode());
        category.setCreateTime(new Date());
        category = categoryRepository.save(category);
        Assert.assertNotNull(category);
        System.out.println(JsonUtil.toJson(category));
    }
}