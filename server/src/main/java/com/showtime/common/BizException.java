package com.showtime.common;

import lombok.Getter;

/**
 * 业务异常
 *
 * Service 层遇到业务规则不满足时，直接抛出此异常，
 * 由 GlobalExceptionHandler 统一处理。
 *
 * 使用示例：
 *   throw new BizException(ResultCode.SEAT_UNAVAILABLE);
 *   throw new BizException(ResultCode.LOGIN_FAILED, "密码错误，还剩3次机会");
 */
@Getter
public class BizException extends RuntimeException {

    private final int code;

    public BizException(ResultCode code) {
        super(code.getMessage());
        this.code = code.getCode();
    }

    public BizException(ResultCode code, String message) {
        super(message);
        this.code = code.getCode();
    }
}
