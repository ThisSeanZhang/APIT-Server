package io.whileaway.apit.shiro.enums.error;

import io.whileaway.apit.base.enums.ResultEnum;

import java.util.stream.Stream;

public enum AuthEnum implements ResultEnum {

    SUCCESS(200, "认证成功"),
    PARAMETER_ERROR(400, "用户名或密码错误"),
    NOT_FOUND(404, "用户不存在"),
    SERVER_ERROR(500, "服务器内部错误"),
    NOT_ALLOW(403, "登入后才可以操作");

    private Integer code;
    private String message;

    AuthEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Stream<ResultEnum> getEnumStream() {
        return Stream.of(values());
    }
}
