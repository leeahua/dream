package com.next.dream.controller.api;

import com.next.dream.dto.TagsDto;
import com.next.dream.enums.CategoryStatusEnum;
import com.next.dream.enums.ResultEnum;
import com.next.dream.service.TagsService;
import com.next.dream.utils.JsonUtil;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 描述：〈标签〉
 *
 * @author liyaohua
 * create on 2018/3/12
 * @version 1.0
 */
@RestController
@RequestMapping("/api/tags")
@Slf4j
public class TagsApiController {
    @Autowired
    private TagsService tagsService;

    /**
     *
     *  获取公共类别列表
     * @return
     * @author liyaohua
     * Created On 2018/3/12 上午9:52
     */
    @PostMapping("/list")
    public ResultVO list(){
        log.info("获取标签列表");
        return ResultVOUtil.success(tagsService.findByStatus(CategoryStatusEnum.ACTIVITY.getCode()));
    }

    /**
     *  添加公共列表
     * @return
     * @author liyaohua
     * Created On 2018/3/12 上午10:11
     */
    @PostMapping("/addandupdate")
    public ResultVO addAndUpdate(@RequestBody @Valid  TagsDto tagsDto, BindingResult result){
        log.info("【添加或修改标签】，参数信息：{}", JsonUtil.toJson(tagsDto));
        if(result.hasErrors()){
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(),ResultVOUtil.getMsg(result));
        }
        return tagsService.addOrUpdate(tagsDto);
    }

    /**
     *  删除类别
     * @return
     * @author liyaohua
     * Created On 2018/3/12 上午10:35
     */
    @PostMapping("/delete")
    public ResultVO delete(@RequestBody TagsDto tagsDto){
        log.info("【删除标签】，参数信息：{}", JsonUtil.toJson(tagsDto));
        tagsService.delete(tagsDto);
        return ResultVOUtil.success();
    }
}
