package com.next.dream.service;

import com.next.dream.domains.Tags;
import com.next.dream.dto.TagsDto;
import com.next.dream.vo.ResultVO;

import java.util.List;

/**
 * 描述：〈标签业务处理〉
 *
 * @author liyaohua
 * create on 2018/3/12
 * @version 1.0
 */
public interface TagsService {

    ResultVO addOrUpdate(TagsDto tagsDto);

    ResultVO delete(TagsDto tagsDto);

    ResultVO list();


    List<Tags> findByStatus(Integer status,TagsDto tagsDto);



}
