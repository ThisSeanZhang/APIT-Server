package io.whileaway.apit.api.request;

import io.whileaway.apit.api.entity.API;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAPI {

    @NotNull(message = "需要传入该参数")
    @NotBlank(message = "不能为空白")
    private String apiName;
    private String method;
    private String bewrite;
    @NotNull(message = "需要传入该参数")
    @NotBlank(message = "不能为空白")
    private String url;
    private String parameters;
    private String headers;
    private String body;
    @NotNull(message="需要指定所属人")
    private Long apiOwner;
    @NotNull(message="需要指定所属的文件夹")
    private Long belongFolder;
    @NotNull(message="需要指定所属的项目")
    private Long belongProject;

    public API covertToAPI () {
        API api = new API(this.apiName, this.method, this.url, this.apiOwner, this.belongFolder, this.belongProject);
        api.setBewrite(this.bewrite);
        api.setParameters(this.parameters);
        api.setHeaders(this.headers);
        api.setBody(this.body);
        return api;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
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

    public Long getBelongFolder() {
        return belongFolder;
    }

    public void setBelongFolder(Long belongFolder) {
        this.belongFolder = belongFolder;
    }

    public Long getBelongProject() {
        return belongProject;
    }

    public void setBelongProject(Long belongProject) {
        this.belongProject = belongProject;
    }
}
