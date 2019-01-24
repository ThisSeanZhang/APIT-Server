package io.whileaway.apit.base;

import io.whileaway.apit.base.enums.ResponseEnum;

public class ResultUtil {

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }

    public static Result error(CommonException e) {
        return error(e.getCode(),e.getMessage());
    }

    public static<T> Result<T> success(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static<T> Result<T> success(Integer code, String message) {
        return success(code, message, null);
    }

    public static<T> Result<T> success(ResponseEnum responseEnum, T data) {
        return success(responseEnum.getCode(), responseEnum.getMessage(), data);
    }

    public static<T> Result<T> success(ResponseEnum responseEnum) {
        return success(responseEnum.getCode(), responseEnum.getMessage(), null);
    }
}
