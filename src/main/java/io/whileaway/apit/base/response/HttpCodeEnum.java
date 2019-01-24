package io.whileaway.apit.base.response;

/**
 * 可进行定义默认的消息
 */
public enum HttpCodeEnum {
    OK(200, "成功"),
    NOTFOUND(404, ""),
    SEE_OTHER(303, "");

    private Integer code;
    private String message;

    HttpCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
