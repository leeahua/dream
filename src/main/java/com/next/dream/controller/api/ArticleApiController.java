package com.next.dream.controller.api;

import com.next.dream.annotation.LoginAnnotation;
import com.next.dream.domains.Article;
import com.next.dream.dto.ArticleDto;
import com.next.dream.dto.UserDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.service.ArticleService;
import com.next.dream.service.RedisService;
import com.next.dream.utils.JsonUtil;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
public class ArticleApiController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisService redisService;

    /**
     * 推荐文章列表
     * @return
     * @author liyaohua
     * Created On 2018/3/8 下午5:52
     */
    @GetMapping("/list")
    public ResultVO list(){
        log.info("文章列表查询");
        return articleService.findBestList();
    }
    /**
     * 添加文章
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/3/5 上午11:14
     */
    @PostMapping("/add")
    @LoginAnnotation
    public ResultVO add(@RequestBody @Valid ArticleDto articleDto,BindingResult result){
        log.info("【添加文章】 参数信息：{}",JsonUtil.toJson(articleDto));
        if(result.hasErrors()){
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(),ResultVOUtil.getMsg(result));
        }
        Article article = articleService.save(articleDto);
        articleDto.setId(article.getId());
        articleDto.setSummary(article.getSummary());
        return ResultVOUtil.success(articleDto);
    }

    /**
     * 更新文章
     *
     * */
    @RequestMapping("/modify")
    @LoginAnnotation
    public ResultVO modify(ArticleDto articleDto){
        log.info("【修改文章】 参数信息：{}",JsonUtil.toJson(articleDto));
        if(articleDto.getId()==null){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        articleService.save(articleDto);
        return ResultVOUtil.success();
    }

    /**
     *  删除文章
     * @return
     * @author liyaohua
     * Created On 2018/3/5 下午2:22
     */
    @PostMapping("/delete")
    @LoginAnnotation
    public ResultVO delete(@RequestBody ArticleDto articleDto, HttpServletRequest request){
        log.info("【删除文章】 参数信息：{}",JsonUtil.toJson(articleDto));
        if(articleDto.getId()==null || StringUtils.isBlank(articleDto.getToken())
                ||StringUtils.isBlank(articleDto.getAuthorName())){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        UserDto user = (UserDto)redisService.get(articleDto.getToken());
        if(articleDto.getAuthorId()!=user.getId()){
            return ResultVOUtil.failed(ResultEnum.USER_TOKEN_UNMATCH);
        }
        return articleService.delete(articleDto.getId());
    }

    /**
     * 根据作者信息获取文章列表
     * @param articleDto
     * @return ResultVO
     * @author liyaohua
     * Created On 2018/3/2 上午10:30
     */
    @PostMapping("/findByAuthorId")
    @LoginAnnotation
    public ResultVO findByAuthorId(@RequestBody ArticleDto articleDto){
        log.info("【根据作者信息获取文章】 参数信息:{}", JsonUtil.toJson(articleDto));
        if(articleDto.getAuthorId() == null){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        return ResultVOUtil.success(articleService.findByAuthorId(articleDto.getAuthorId()));
    }
    /**
     *
     * 根据文章标签/类型获取文章列表
     * @param articleDto
     * @return ResultVO
     * @author liyaohua
     * Created On 2018/3/5 上午11:11
     */
    @PostMapping("/fingByCateIds")
    public ResultVO findByCateIds(@RequestBody ArticleDto articleDto){
        log.info("【根据类型获取文章列表】参数信息:{}",JsonUtil.toJson(articleDto));
        if(articleDto.getCateIds() == null){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        return ResultVOUtil.success(articleService.findByCateIds(articleDto.getCateIds()));
    }


}
