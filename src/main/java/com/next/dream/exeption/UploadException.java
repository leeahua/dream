package com.next.dream.exeption;

/**
 * 描述：〈文件异常捕获〉
 *
 * @author liyaohua
 * create on 2018/4/10
 * @version 1.0
 */
public class UploadException extends Exception{
    static final long serialVersionUID = 8426423106453163293L;

    public UploadException(String s) {
        super(s);
    }
}
