package io.whileaway.apit.base;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CommonHandle {

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public Result commonHandle(CommonException commonException, HttpServletResponse response) {
//        CommonException commonException = (CommonException) e;
        response.setStatus(commonException.getCode());
        return ResultUtil.error(commonException);
    }
}
