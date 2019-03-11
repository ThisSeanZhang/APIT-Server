package io.whileaway.apit.api.entity;

import io.whileaway.apit.api.enums.StatusDict;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    private String projectName;
    private Long projectOwner;
    private Boolean overt;
    private String whoJoins;
    private Integer status;

    public Project() { }

    public Project(String projectName, Long projectOwner) {
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.overt = false;
        this.whoJoins = String.valueOf(this.projectOwner);
        this.status = StatusDict.NORMAL.getCode();
    }

    public Project(String projectName, Long projectOwner, Boolean overt, String whoJoins) {
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.overt = overt;
        this.whoJoins = whoJoins;
        this.status = StatusDict.NORMAL.getCode();
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

    @Override
    public String toString() {
        return "Project{" +
                "pid=" + pid +
                ", projectName='" + projectName + '\'' +
                ", projectOwner=" + projectOwner +
                ", overt=" + overt +
                ", whoJoins='" + whoJoins + '\'' +
                ", status=" + status +
                '}';
    }
}
