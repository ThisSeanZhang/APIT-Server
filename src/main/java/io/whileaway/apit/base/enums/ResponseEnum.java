package io.whileaway.apit.base.enums;

import java.util.stream.Stream;

public enum ResponseEnum implements ResultEnum {
    SUCCESS(200, "成功");

    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
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
