package io.whileaway.apit.api.request;

import io.whileaway.apit.api.entity.Project;
import org.hibernate.validator.constraints.Length;

public class ModifyProject {

    private Long pid;
    @Length(max = 8, min = 1, message = "项目名称在1-8个字符之间")
    private String projectName;
    private Long projectOwner;
    private Boolean overt;
    private String whoJoins;

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

    public Project convertToProject() {
        return new Project(this.projectName, this.projectOwner, this.overt, this.whoJoins);
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
