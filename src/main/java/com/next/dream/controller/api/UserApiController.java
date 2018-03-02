package com.next.dream.controller.api;

import com.next.dream.dto.UserDto;
import com.next.dream.enums.ResultEnum;
import com.next.dream.service.UserService;
import com.next.dream.utils.EnAndDecryptUtils;
import com.next.dream.utils.JsonUtil;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 描述：〈用户控制器〉
 *
 * @author liyaohua
 * @create 2018/2/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    @Autowired
    private UserService userService;
    /**
     * 登陆处理
     * @param userDto
     * @author liyaohua
     * Created On 2018/2/26 下午3:58
     */
    @PostMapping("/login")
    public ResultVO login(@RequestBody @Valid UserDto userDto, BindingResult result, HttpServletRequest request){
        log.info("【登陆】 接收参数:{}",JsonUtil.toJson(userDto));
        if(result.hasErrors()){
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultVOUtil.getMsg(result));
        }
        //判断是否已经登陆过
        String token = EnAndDecryptUtils.md5Encrypt(userDto.getUsername());
        UserDto user = (UserDto) request.getSession().getAttribute(token);
        if(user != null){
            return ResultVOUtil.success(user);
        }
        ResultVO resultVO = userService.login(userDto.getUsername(),userDto.getPassword(),token);
        if(resultVO.getCode().equals(ResultEnum.SUCCESS.getCode())){
            request.getSession().setAttribute(token,userDto);
        }
        return resultVO;
    }

    @PostMapping("/checklogin")
    public ResultVO checkLogin(@RequestBody UserDto userDto,HttpServletRequest request){
        log.info("【校验用户登陆】 参数信息：{}:",JsonUtil.toJson(userDto));
        if(userDto.getUsername() == null || userDto.getToken() ==null){
            return ResultVOUtil.failed(ResultEnum.PARAM_ERROR);
        }
        if(!EnAndDecryptUtils.md5Encrypt(userDto.getUsername()).equals(userDto.getToken())){
            return ResultVOUtil.failed(ResultEnum.USER_TOKEN_UNMATCH);
        }
        UserDto user = (UserDto)request.getSession().getAttribute(userDto.getToken());

        if(user!=null){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.failed(ResultEnum.USER_UNLOGIN_ERROR);
    }

    /**
     * 注册处理
     * @param user 用户信息
     * @author liyaohua
     * Created On 2018/2/26 下午3:58
     */
    @PostMapping("/register")
    public ResultVO register(@RequestBody  @Valid UserDto user, BindingResult result){
        log.info("【注册】 接收参数:{}", JsonUtil.toJson(user));
        if(result.hasErrors()){
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultVOUtil.getMsg(result));
        }
        return userService.save(user);
    }
    /**
     * 用户名校验处理
     * @param username 用户信息
     * @author liyaohua
     * Created On 2018/2/26 下午3:58
     */
    @GetMapping("/checkusername")
    public ResultVO checkUsername(@RequestParam String username){
        log.info("【校验用户名】 接收参数 username:{}", username);
        return userService.checkUsername(username);
    }

    /**
     * 邮箱校验处理
     * @param email 用户信息
     * @author liyaohua
     * Created On 2018/2/26 下午3:58
     */
    @GetMapping("/checkemail")
    public ResultVO checkEmail(@RequestParam String email){
        log.info("【校验邮箱】 接收参数 email:{}", email);
        return userService.checkEmail(email);
    }

    /**
     * 激活码校验处理
     * @param username 用户信息
     * @param activateCode 激活码
     * @author liyaohua
     * Created On 2018/2/26 下午3:58
     */
    @GetMapping("/checkactivatecode")
    public ResultVO checkActivateCode(@RequestParam String username, @RequestParam String activateCode){
        log.info("【激活用户】接收参数 username:{},activateCode:{}",username,activateCode);
        return  userService.checkCode(username,activateCode);
    }

}
