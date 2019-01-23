package io.whileaway.apit.base;

import io.whileaway.apit.base.enums.ResultEnum;

public class CommonException extends RuntimeException{

    private Integer code;

    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
