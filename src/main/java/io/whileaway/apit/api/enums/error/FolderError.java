package io.whileaway.apit.api.enums.error;

import io.whileaway.apit.base.enums.ResultEnum;

import java.util.stream.Stream;

public enum  FolderError implements ResultEnum {
    NOT_FOUND(404, "(っ °Д °;)っ找不到你想要的文件夹欸"),
    INSUFFICIENT_STORAGE(507, "创建失败"),
    PROJECT_EMPTY(404, "还没有项目诶"),
    NOT_ALLOW_SELF_FATHER(400,"自己不能做自己的父文件夹的,这是会报错的"),
    FOLDER_MUST_HAVE_BELONG_FOLDER(400,"每个文件夹都是离不开项目的"),
    NOT_ALLOW_SON_BE_FATHER(400,"自己的子文件夹不能做自己的父文件夹的,这是会报错的")
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
