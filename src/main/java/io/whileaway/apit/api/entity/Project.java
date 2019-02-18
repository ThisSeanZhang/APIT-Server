package io.whileaway.apit.api.entity;

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

    public Project() { }

    public Project(String projectName, Long projectOwner) {
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.overt = false;
        this.whoJoins = String.valueOf(this.projectOwner);
    }

    public Project(String projectName, Long projectOwner, Boolean overt, String whoJoins) {
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.overt = overt;
        this.whoJoins = whoJoins;
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
}
