package io.whileaway.apit.account.enums.error;

import io.whileaway.apit.base.enums.ResultEnum;

import java.util.stream.Stream;

public enum DeveloperError implements ResultEnum {
    NOT_FOUND(404, "(っ °Д °;)っ找不到你想要的人欸"),
    NAME_CONFLICT(409, "此用户名已经被使用了"),
    INSUFFICIENT_STORAGE(507, "创建失败"),
    PROJECT_EMPTY(404, "还没有项目诶"),
    ;

    private Integer code;
    private String message;

    DeveloperError(Integer code, String message) {
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
