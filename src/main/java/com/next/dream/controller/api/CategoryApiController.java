package com.next.dream.controller.api;

import com.next.dream.dto.CategoryDto;
import com.next.dream.enums.CategoryStatusEnum;
import com.next.dream.service.CategoryService;
import com.next.dream.utils.JsonUtil;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共类别处理
 *
 * @author liyaohua
 * create on 2018/3/9
 * @version 1.0
 */
@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryApiController {


    @Autowired
    private CategoryService categoryService;

    /**
     *
     *  获取公共类别列表
     * @return
     * @author liyaohua
     * Created On 2018/3/12 上午9:52
     */
    @PostMapping("/list")
    public ResultVO list(){
        log.info("获取类别列表");
        return ResultVOUtil.success(categoryService.findByStatus(CategoryStatusEnum.ACTIVITY.getCode()));
    }

    /**
     *  添加公共列表
     * @return
     * @author liyaohua
     * Created On 2018/3/12 上午10:11
     */
    @PostMapping("/addandupdate")
    public ResultVO addAndUpdate(@RequestBody CategoryDto category){
        log.info("【添加或修改类别】，参数信息：{}", JsonUtil.toJson(category));
        return categoryService.addOrUpdate(category);
    }

    /**
     *  删除类别
     * @return
     * @author liyaohua
     * Created On 2018/3/12 上午10:35
     */
    @PostMapping("/delete")
    public ResultVO delete(@RequestBody CategoryDto category){
        log.info("【删除类别】，参数信息：{}", JsonUtil.toJson(category));
        categoryService.delete(category);
        return ResultVOUtil.success();
    }


}
