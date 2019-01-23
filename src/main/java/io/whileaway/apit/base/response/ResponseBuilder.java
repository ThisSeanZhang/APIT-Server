package io.whileaway.apit.base.response;


import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ErrorResponse;

import java.util.function.Supplier;

public class ResponseBuilder <T>{
    private final Response in;

    public ResponseBuilder(Supplier<Response> testResponse) {
        this.in = testResponse.get();
    }

    private ResponseBuilder(Response response) {
        this.in = response;
    }

    public ResponseBuilder<Response> setHttpStatus(HttpCodeEnum httpCodeEnum) {
        in.setCode(httpCodeEnum.getCode());
        return new ResponseBuilder(in);
    }

    public ResponseBuilder<T> setMessage(TestResponseEnum testResponseEnum) {
        in.setMessage(testResponseEnum.getMessage());
        return new ResponseBuilder<T>(in);
    }

    public ResponseBuilder<T> setData(T t) {
        in.setData(t);
        return new ResponseBuilder<T>(in);
    }

    public Response ok() {
        return this.in;
    }

    public void error() {
        throw new CommonException(ErrorResponse.ERROR);
    }
}
