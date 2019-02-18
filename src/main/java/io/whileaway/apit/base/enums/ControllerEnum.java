package io.whileaway.apit.base.enums;

import java.util.stream.Stream;

public enum ControllerEnum implements ResultEnum{
    SUCCESS(200, "成功"),
    PARAMETER_ERROR(400, "参数错误请检查参数"),
    NOT_FOUND(404, "请求的资源不存在"),
    SERVER_ERROR(500, "服务器内部错误"),
    NOT_ALLOW(403, "不允许此操作");

    private Integer code;
    private String message;

    ControllerEnum(Integer code, String message) {
        this.code = code;
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
