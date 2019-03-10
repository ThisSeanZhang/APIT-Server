package io.whileaway.apit.api.response;

import io.whileaway.apit.api.entity.Project;

import java.util.Map;
import java.util.Optional;

public class ProjectVO {

    private Long pid;
    private String projectName;
    private Long projectOwner;
    private Boolean overt;
    private String whoJoins;
    private Integer status;
    private String ownerName;

    public ProjectVO(Project project) {
        this.pid = project.getPid();
        this.projectName = project.getProjectName();
        this.projectOwner = project.getProjectOwner();
        this.overt = project.getOvert();
        this.whoJoins = project.getWhoJoins();
        this.status = project.getStatus();
    }

    public void injectName (Map<Long, String> idName) {
        this.ownerName = Optional.ofNullable(idName.get(projectOwner)).orElse(null);
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(Long projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Boolean getOvert() {
        return overt;
    }

    public void setOvert(Boolean overt) {
        this.overt = overt;
    }

    public String getWhoJoins() {
        return whoJoins;
    }

    public void setWhoJoins(String whoJoins) {
        this.whoJoins = whoJoins;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
