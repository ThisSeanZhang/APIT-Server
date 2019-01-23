package io.whileaway.apit.base.enums;

import java.util.stream.Stream;

public enum  ErrorResponse implements ResultEnum {

    ERROR(500,"测试性抛出服务器内部错误");
    private Integer code;
    private String message;

    ErrorResponse(Integer code, String message) {
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
