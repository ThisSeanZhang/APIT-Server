package io.whileaway.apit.base.response;

public class Response<T> {

    private Integer code;
    private String message;
    private T data;
    private String redirect;

    public Response() {
        this.code = HttpCodeEnum.OK.getCode();
        this.message = ControllerMessage.SUCCESS.getMessage();
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
