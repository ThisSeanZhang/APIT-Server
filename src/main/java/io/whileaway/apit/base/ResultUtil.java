package io.whileaway.apit.base;

import io.whileaway.apit.base.enums.ResponseEnum;

public class ResultUtil {

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result error(CommonException e) {
        return error(e.getCode(),e.getMessage());
    }

    public static Result success(Integer code, String message, Object o) {
        Result result = new Result();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(o);
        return result;
    }

    public static Result success(Integer code, String message) {
        return success(code, message, null);
    }

    public static Result success(ResponseEnum responseEnum, Object o) {
        return success(responseEnum.getCode(), responseEnum.getMessage(), o);
    }

    public static Result success(ResponseEnum responseEnum) {
        return success(responseEnum.getCode(), responseEnum.getMessage(), null);
    }
}
