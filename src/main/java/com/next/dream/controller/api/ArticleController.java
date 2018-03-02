package com.next.dream.controller.api;

import com.next.dream.service.ArticleService;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：〈文章〉
 *
 * @author liyaohua
 * create on 2018/2/26
 * @version 1.0
 */
@RestController
@RequestMapping("/api/article")
@Slf4j
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    /**
     *  根据作者信息获取文章列表
     * @return
     * @author liyaohua
     * Created On 2018/3/2 上午10:30
     */
    @GetMapping("/findByAuthorId")
    public ResultVO findByAuthorId(@RequestBody Integer authorId){
        log.info("【根据作者信息获取文章】 authorId:{}",authorId);
        return ResultVOUtil.success(articleService.findByAuthorId(authorId));
    }

    @GetMapping("/fingByCateIds")
    public ResultVO findByCateIds(@RequestBody String cateIds){
        log.info("【根据类型获取文章列表】cateIds:{}",cateIds);
        return ResultVOUtil.success(articleService.findByCateIds(cateIds));
    }



}
