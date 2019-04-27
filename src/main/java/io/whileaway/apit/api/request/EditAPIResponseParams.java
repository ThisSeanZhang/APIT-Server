package io.whileaway.apit.api.request;

public class EditAPIResponseParams {

    private Long aid;
    private String params;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "EditAPIResponseParams{" +
                "aid=" + aid +
                ", params='" + params + '\'' +
                '}';
    }
}
