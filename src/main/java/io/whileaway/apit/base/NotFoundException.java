package io.whileaway.apit.base;

import io.whileaway.apit.base.enums.ResultEnum;

public class NotFoundException extends CommonException{

    public NotFoundException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public NotFoundException(String message) {
        super(404, message);
    }

    public NotFoundException() {
        super(404, "找不到你想要的欸");
    }
}
