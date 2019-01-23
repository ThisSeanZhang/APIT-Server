package io.whileaway.apit.base;

public class Result<T> {

    private Integer status;
    private String message;
    private T data;
    private String redirct;

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

    public String getRedirct() {
        return redirct;
    }

    public void setRedirct(String redirct) {
        this.redirct = redirct;
    }
}