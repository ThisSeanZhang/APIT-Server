package io.whileaway.apit.api.request;

public class FilterFolder {

    private String folderName;
    private Long parentId;
    private Long folderOwnerId;
    private Long belongProject;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getFolderOwnerId() {
        return folderOwnerId;
    }

    public void setFolderOwnerId(Long folderOwnerId) {
        this.folderOwnerId = folderOwnerId;
    }

    public Long getBelongProject() {
        return belongProject;
    }

    public void setBelongProject(Long belongProject) {
        this.belongProject = belongProject;
    }
}
