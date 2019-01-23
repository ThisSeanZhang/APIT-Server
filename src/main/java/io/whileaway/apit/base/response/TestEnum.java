package io.whileaway.apit.base.response;

import java.util.stream.Stream;

public enum TestEnum {
    NOT_FOUND(HttpCodeEnum.NOTFOUND, "WAHT");

    private Integer code;
    private String message;


    TestEnum(HttpCodeEnum code, String message) {
        this.code = code.getCode();
        this.message = message;
    }

    public Integer getCode(Integer code) {
        return this.code;
    }

    public TestEnum getEnum(HttpCodeEnum code){
        return Stream.of(values())
                .findFirst( en -> code.getCode() == en.getCode()).get();

    }
}
