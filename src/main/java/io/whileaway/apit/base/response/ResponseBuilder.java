package io.whileaway.apit.base.response;


import java.util.function.Supplier;

public class ResponseBuilder <T extends TestResponse>{
    private final T in;

    public ResponseBuilder(Supplier<T> t) {
        this.in = t.get();
    }

    public ResponseBuilder(T t) {
        this.in = t;
    }

    public ResponseBuilder<T> setHttpStatus(HttpCodeEnum httpCodeEnum) {
        in.setCode(httpCodeEnum.getCode());
        return new ResponseBuilder(in);
    }

    public ResponseBuilder<T> setMessage(TestResponseEnum testResponseEnum) {
        in.setMessage(testResponseEnum.getMessage());
        return new ResponseBuilder<T>(in);
    }

    public T build() {
        return this.in;
    }
}
