package io.whileaway.apit.account.enums.error;

import io.whileaway.apit.base.enums.ResultEnum;

import java.util.stream.Stream;

public enum SessionError implements ResultEnum {
    NOT_FOUND(404, "(っ °Д °;)っSession已经过期了"),

    ;

    private Integer code;
    private String message;

    SessionError(Integer code, String message) {
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
