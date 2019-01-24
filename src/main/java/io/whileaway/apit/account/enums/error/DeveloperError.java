package io.whileaway.apit.account.enums.error;

import io.whileaway.apit.base.enums.ErrorResponse;
import io.whileaway.apit.base.enums.ResultEnum;

import java.util.stream.Stream;

public enum DeveloperError implements ResultEnum {
    NOTFOUND(404, "查无此人");

    private Integer code;
    private String message;

    DeveloperError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Stream<ResultEnum> getEnumStream() {
        return null;
    }
}
