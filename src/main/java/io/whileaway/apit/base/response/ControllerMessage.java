package io.whileaway.apit.base.response;

public enum ControllerMessage implements TestResponseEnum{
    SUCCESS("成功");

    private String message;

    ControllerMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
