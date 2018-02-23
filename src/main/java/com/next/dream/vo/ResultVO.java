package com.next.dream.vo;

/**
 * 描述：〈返回结果〉
 *
 * @author liyaohua
 * @create 2018/2/11
 * @since 1.0.0
 */
public class ResultVO {

    private String code;
    private String msg;
    private Object data;

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultVO(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
