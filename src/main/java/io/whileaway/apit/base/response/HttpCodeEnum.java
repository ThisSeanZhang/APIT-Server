package io.whileaway.apit.base.response;

public enum HttpCodeEnum {
    OK(200),
    NOTFOUND(404);

    private Integer code;

    HttpCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode(){
        return this.code;
    }
}
