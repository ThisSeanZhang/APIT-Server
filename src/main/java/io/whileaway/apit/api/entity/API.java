package io.whileaway.apit.api.entity;

import javax.persistence.*;

@Entity
public class API {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    private String apiName;
    private String method;
    private String bewrite;
    private String url;
    @Column(columnDefinition="text")
    private String parameters;
    @Column(columnDefinition="text")
    private String headers;
    @Column(columnDefinition="text")
    private String body;
    private Long apiOwner;
    private Long belongNode;
    private Long belongProject;

    public API(String apiName, String method, String url, Long apiOwner, Long belongNode, Long belongProject) {
        this.apiName = apiName;
        this.method = method;
        this.url = url;
        this.apiOwner = apiOwner;
        this.belongNode = belongNode;
        this.belongProject = belongProject;
    }

    public API() {
    }

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

    public Long getBelongNode() {
        return belongNode;
    }

    public void setBelongNode(Long belongNode) {
        this.belongNode = belongNode;
    }

    public Long getBelongProject() {
        return belongProject;
    }

    public void setBelongProject(Long belongProject) {
        this.belongProject = belongProject;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
