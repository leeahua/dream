package com.next.dream.enums;

import lombok.Getter;

/**
 * 描述：结果响应
 *
 * @author liyaohua
 * @since 1.0.0
 */
@Getter
public enum ResultEnum {
    SUCCESS("0000","处理成功"),
    FAILED("0001","处理失败"),
    PRODUCT_NOT_EXISTS("0003","商品信息不存在"),
    PRODUCT_STOCK_ERROR("0004", "商品库存不足"),
    PRODUCT_ORDER_NOT_EXISTS("0005", "订单号不存在"),
    PRODUCT_ORDER_STATUS_ERROR("0006", "订单状态不正确"),
    PRODUCT_ORDER_UPDATE_ERROR("0007", "订单更新失败"),
    PRODUCT_ORDER_DETAIL_NOT_EXISTS("0008", "订单商品详情数据不存在"),
    PRODUCT_ORDER_PAY_STATUS_ERROR("0009","订单支付状态不正确" ),
    PARAM_ERROR("0010","参数有误"),
    CART_EMPTY("0011", "购物车为空"),
    USER_NOT_EXISTS("0012","用户不存在"),
    USER_PASSWORD_ERROR("0013", "密码错误"),
    USER_ILLEGAL("0014","该用户已禁用" ),
    USER_EXISTS_ALREADY("0015", "用户已存在"),
    USER_ACTIVICATE_ALREADY("0016", "用户已经激活成功" ),
    USER_ACTIVICATE_CODE_EXPIRE("0017", "验证码已经过期"),
    USER_EMAIL_EXISTS_ALREADY("0018", "邮箱已经被注册"),
    USER_ACTIVICATE_CODE_UNMATCH("0019", "用户名和激活码不匹配"),
    USER_UNLOGIN_ERROR("0020", "用户未登陆"),
    SERVICE_EXCEPTION("0021", "服务器内部异常"),
    USER_TOKEN_UNMATCH("0022", "用户token不匹配"),
    ARTICLE_NOT_EXISTS("0023", "对应文章不存在"),
    CATEGORY_NOT_EXISTS("0024", "类别不存在");

    private String code;
    private String message;
    ResultEnum(String code,String message){
        this.code = code;
        this.message = message;
    }
}
