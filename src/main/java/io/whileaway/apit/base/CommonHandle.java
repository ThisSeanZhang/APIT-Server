package io.whileaway.apit.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

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

    @ExceptionHandler(value = InvalidFormatException.class)
    @ResponseBody
    public Result jsonHandle(InvalidFormatException exception, HttpServletResponse response) {
        response.setStatus(400);
        return ResultUtil.success(200,"测试",ResultUtil.invalidFormatDataMessage(exception.getMessage()));
//        return ResultUtil.success(200,"测试",exception.getMessage());
    }
}
