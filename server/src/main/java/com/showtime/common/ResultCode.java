package com.showtime.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一响应码枚举
 *
 * 分段规则：
 *   200        — 成功
 *   4000-4999  — 客户端错误（参数校验、权限、业务限制）
 *   5000-5999  — 服务端错误
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    // ==================== 成功 ====================
    SUCCESS(200, "操作成功"),

    // ==================== 客户端错误 4xxx ====================
    BAD_REQUEST(4000, "请求参数有误"),
    UNAUTHORIZED(4001, "未登录或登录已过期"),
    FORBIDDEN(4003, "无权限访问"),
    NOT_FOUND(4004, "资源不存在"),
    PHONE_EXISTS(4010, "手机号已被注册"),
    EMAIL_EXISTS(4011, "邮箱已被注册"),
    LOGIN_FAILED(4012, "手机号或密码错误"),
    SEAT_UNAVAILABLE(4020, "该座位不可选"),
    SEAT_LOCKED_BY_OTHER(4021, "座位已被他人锁定"),
    LOCK_TIMEOUT(4022, "选座超时，请重新选择"),
    ORDER_NOT_FOUND(4030, "订单不存在"),
    ORDER_CANNOT_PAY(4031, "该订单无法支付"),
    ORDER_CANNOT_CANCEL(4033, "该订单无法取消"),
    PAYMENT_FAILED(4040, "支付失败"),

    // ==================== 服务端错误 5xxx ====================
    INTERNAL_ERROR(5000, "服务器内部错误"),
    DATABASE_ERROR(5001, "数据库操作失败");

    private final int code;
    private final String message;
}
