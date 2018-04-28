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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/list")
    public ResultVO list(@RequestParam(value = "size",defaultValue = "3")Integer size,@RequestParam(value = "no",defaultValue = "0")Integer no){
        Pageable pageable = new PageRequest(no,size);
        log.info("文章列表查询:size:{},no:{}",size,no);
        return ResultVOUtil.success(articleService.findBestList(pageable));
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
    public ResultVO delete(@RequestBody ArticleDto articleDto){
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
        if(articleDto.getAuthorId() == null ){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        Page<Article> pages = articleService.findByAuthorId(articleDto);
        return ResultVOUtil.success(pages);
    }
    /**
     * @Description: 根据作者id和token获取未发布文章详情 需要验证
     * @param articleDto
     * @return ResultVO
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/4/8 下午3:08
     */
    @PostMapping("/findDetailByAuthorId")
    @LoginAnnotation
    public ResultVO findDetailByAuthodId(@RequestBody ArticleDto articleDto){
        log.info("【根据作者信息获取文章】 参数信息:{}", JsonUtil.toJson(articleDto));
        if(articleDto.getAuthorId() == null  || articleDto.getId()==null){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        ResultEnum resultEnum = checkUser(articleDto,true,null);
        if(resultEnum!=null){
            return ResultVOUtil.failed(resultEnum);
        }
        return ResultVOUtil.success(articleService.findDetailByAuthorId(articleDto));
    }

    private ResultEnum checkUser(ArticleDto articleDto,boolean isNeedCheckUser,String[] needCheckParams){
        if(isNeedCheckUser){
            UserDto user = (UserDto) redisService.get(articleDto.getToken());
            log.info("user:{}",user);
            if(user==null){
                return ResultEnum.USER_UNLOGIN_ERROR;
            }
            if(user!=null && user.getId().intValue()!=articleDto.getAuthorId().intValue()){
                return ResultEnum.USER_NOT_MATCH;
            }
        }
        return null;
    }

    /**
     * @Description: 获取发布的文章详情 不需要登陆
     * @param articleDto
     * @return ResultVO
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/4/8 下午3:19
     */
    @PostMapping("/findDetail")
    public ResultVO findDetail(@RequestBody ArticleDto articleDto){
        log.info("【根据作者信息获取文章】 参数信息:{}", JsonUtil.toJson(articleDto));
        if(articleDto.getId()==null){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        return ResultVOUtil.success(articleService.findById(articleDto.getId()));
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
