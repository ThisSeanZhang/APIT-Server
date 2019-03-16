package io.whileaway.apit.api.enums.error;

import io.whileaway.apit.base.enums.ResultEnum;

import java.util.stream.Stream;

public enum  FolderError implements ResultEnum {
    NOT_FOUND(404, "(っ °Д °;)っ找不到你想要的文件夹欸"),
    INSUFFICIENT_STORAGE(507, "创建失败"),
    PROJECT_EMPTY(404, "还没有项目诶"),
    NOT_ALLOW_SELF_FATHER(400,"(→_→)自己不能做自己的父文件夹的,这是会报错的")
            ;

    private Integer code;
    private String message;

    FolderError(Integer code, String message) {
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
