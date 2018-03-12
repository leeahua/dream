package com.next.dream.service.impl;

import com.next.dream.domains.Tags;
import com.next.dream.dto.TagsDto;
import com.next.dream.enums.CategoryStatusEnum;
import com.next.dream.enums.ResultEnum;
import com.next.dream.repository.TagsRepository;
import com.next.dream.service.TagsService;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述：〈标签业务类实现〉
 *
 * @author liyaohua
 * create on 2018/3/12
 * @version 1.0
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public ResultVO addOrUpdate(TagsDto tagsDto) {
        Tags tags ;
        if(tagsDto.getId()!=null){
            tags = tagsRepository.findOne(tagsDto.getId());
            if(tags==null){
                return ResultVOUtil.failed(ResultEnum.DATA_NOT_EXISTS);
            }
            tags.setTagName(tagsDto.getTagName());
            tags.setUpdateTime(new Date());
        }else{
            tags = new Tags();
            tags.setUserId(tagsDto.getUserId());
            tags.setTagName(tagsDto.getTagName());
            tags.setStatus(CategoryStatusEnum.ACTIVITY.getCode());
            tags.setUpdateTime(new Date());
        }
        tagsRepository.save(tags);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO delete(TagsDto tagsDto) {
        Tags tags = new Tags();
        tags.setId(tagsDto.getId());
        tags.setStatus(CategoryStatusEnum.DISABLED.getCode());
        tagsRepository.save(tags);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO list() {
        return ResultVOUtil.success(tagsRepository.findAll());
    }

    @Override
    public List<Tags> findByStatus(Integer status,TagsDto tagsDto) {
        return tagsRepository.findByStatusAndUserId(status,tagsDto.getUserId());
    }
}
