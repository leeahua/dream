package com.next.dream.utils;

import com.next.dream.enums.ResultEnum;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 描述：〈响应信息封装〉
 *
 * @author liyaohua
 * @create 2018/1/31
 * @since 1.0.0
 */
@Slf4j
public class ResultVOUtil {

    private static final Locale LOCALE = Locale.getDefault();

    public static String getMsg(BindingResult result) {
        if (result == null)
            return "";
        List<FieldError> allErrors = result.getFieldErrors();
        if (allErrors == null || allErrors.isEmpty())
            return "";

        StringBuffer sb = new StringBuffer();
        for (FieldError fieldError : allErrors) {
            sb.append(fieldError.getField());
            if (!Locale.CHINA.equals(LOCALE))
                sb.append(" ");
            sb.append(fieldError.getDefaultMessage()).append("; ");
        }
        return sb.toString();
    }

    /**
     *   <br>
     * 有返回值成功响应
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午4:33
     */

    public static ResultVO success(Object data){
        if(data instanceof Page){
            //则格式化Page
            Page page = (Page)data;
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("rows",page.getContent());
            dataMap.put("no",page.getNumber());
            dataMap.put("size",page.getSize());
            dataMap.put("firstPage",page.isFirst());
            dataMap.put("endPage",page.isLast());
            dataMap.put("pageCount",page.getTotalPages());
            dataMap.put("rowCount",page.getTotalElements());
            ResultVO resultVO = new ResultVO();
            resultVO.setCode(ResultEnum.SUCCESS.getCode());
            resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
            resultVO.setData(dataMap);
            return resultVO;
        }
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
        resultVO.setData(data);
        return resultVO;
    }
    /**
     *   <br>
     * 无返回值成功响应
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午4:55
     */
    public static ResultVO success(){
        return success(null);
    }
    /**
     *   <br>
     *  失败响应信息
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午4:55
     */
    public static ResultVO failed(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        resultVO.setData(null);
        return resultVO;
    }
}
