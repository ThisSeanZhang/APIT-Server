package io.whileaway.apit.base.response;

public enum SuccessRespomse implements TestResponseEnum{
    SUCCESS("成功");

    private String message;

    SuccessRespomse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
