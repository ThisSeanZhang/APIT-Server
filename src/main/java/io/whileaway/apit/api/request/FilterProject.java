package io.whileaway.apit.api.request;

public class FilterProject {

    private String projectName;
    private Long owner;
    private Boolean overt;

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Boolean getOvert() {
        return overt;
    }

    public void setOvert(Boolean overt) {
        this.overt = overt;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
