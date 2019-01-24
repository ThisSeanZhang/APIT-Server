package io.whileaway.apit.base;

public class Result<T> {

    private Integer status;
    private String message;
    private T data;
    private String redirect;

    public Result() {
    }

    public Result(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(String message, T data, String redirect) {
        this.message = message;
        this.data = data;
        this.redirect = redirect;
    }

    public Result(Integer code, String message, T data, String redirect) {
        this.status = code;
        this.message = message;
        this.data = data;
        this.redirect = redirect;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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