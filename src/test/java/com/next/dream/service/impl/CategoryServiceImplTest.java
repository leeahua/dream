package com.next.dream.service.impl;

import com.next.dream.enums.CategoryStatusEnum;
import com.next.dream.service.CategoryService;
import com.next.dream.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findAll() {
        Assert.assertNotNull(categoryService.findAll());
        log.info("结果信息：{}", JsonUtil.toJson(categoryService.findAll()));
    }

    @Test
    public void findByStatus() {
        log.info(JsonUtil.toJson(categoryService.findByStatus(CategoryStatusEnum.DISABLED.getCode())));
        Assert.assertNotNull(categoryService.findByStatus(CategoryStatusEnum.DISABLED.getCode()));
    }
    @Test
    public void disableCategory() {
        log.info(JsonUtil.toJson(categoryService.disableCategory(5)));
    }
}