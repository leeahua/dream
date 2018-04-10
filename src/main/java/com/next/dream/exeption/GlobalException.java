package com.next.dream.exeption;

import com.next.dream.enums.ResultEnum;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * 描述：〈全局异常处理〉
 *
 * @author liyaohua
 * create on 2018/4/10
 * @version 1.0
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MultipartException.class)
    public ResultVO handleSizeLimitExceededException(MultipartException sizeLimitExceededException){
        log.error("文件上传异常，异常信息：{}",sizeLimitExceededException.getMessage());
        return ResultVOUtil.failed(ResultEnum.FILE_SIZE_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UploadException.class)
    public ResultVO handleFileException(UploadException fileExceprion){
        log.error("【文件格式异常】", fileExceprion);
        return ResultVOUtil.failed(ResultEnum.FILE_UNSUPPORY_ERROR);
    }

}
