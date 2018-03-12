package com.next.dream.service.impl;

import com.next.dream.dto.TagsDto;
import com.next.dream.enums.CategoryStatusEnum;
import com.next.dream.repository.TagsRepository;
import com.next.dream.service.TagsService;
import com.next.dream.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TagsServiceImplTest {

    @Autowired
    private TagsService tagsService;

    @Autowired
    private TagsRepository tagsRepository;


    @Test
    public void addAndUpdate() {
        TagsDto tags = new TagsDto();
        tags.setStatus(CategoryStatusEnum.ACTIVITY.getCode());
        tags.setTagName("互联网");
        tags.setUserId(2);
        tags.setUpdateTime(new Date());
        tagsService.addOrUpdate(tags);
        log.info(JsonUtil.toJson(tagsService.list()));
    }

    @Test
    @Transactional
    public void delete() {
        TagsDto tagsDto = new TagsDto();
        tagsDto.setId(2);
        tagsService.delete(tagsDto);
        log.info(JsonUtil.toJson(tagsRepository.findOne(2)));
    }

    @Test
    public void list() {
        Assert.assertNotNull(tagsService.list());
    }
}