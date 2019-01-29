package io.whileaway.apit.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class API {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    private String method;
    private String bewrite;
    private String url;
    private String parameters;
    private String headers;
    private String body;
    private Long apiOwner;
    private Long belongFloder;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBewrite() {
        return bewrite;
    }

    public void setBewrite(String bewrite) {
        this.bewrite = bewrite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getApiOwner() {
        return apiOwner;
    }

    public void setApiOwner(Long apiOwner) {
        this.apiOwner = apiOwner;
    }

    public Long getBelongFloder() {
        return belongFloder;
    }

    public void setBelongFloder(Long belongFloder) {
        this.belongFloder = belongFloder;
    }
}
