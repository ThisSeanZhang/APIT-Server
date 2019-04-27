package io.whileaway.apit.api.request;

public class FilterProject {

    private String projectName;
    private Long owner;
    private Long whoJoin;
    private Boolean overt;

    public FilterProject() {
    }

    public FilterProject(String projectName, Long owner, Long whoJoin, Boolean overt) {
        this.projectName = projectName;
        this.owner = owner;
        this.whoJoin = whoJoin;
        this.overt = overt;
    }

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

    public Long getWhoJoin() {
        return whoJoin;
    }

    public void setWhoJoin(Long whoJoin) {
        this.whoJoin = whoJoin;
    }
}
