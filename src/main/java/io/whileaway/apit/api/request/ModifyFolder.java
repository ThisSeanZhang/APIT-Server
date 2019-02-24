package io.whileaway.apit.api.request;

import io.whileaway.apit.api.entity.Folder;

public class ModifyFolder {
    private Long fid;
    private String folderName;
    private Long parentId;
    private Long belongProject;


    public Folder convertToFolder () {
        Folder folder = new Folder();
        folder.setFid(this.fid);
        folder.setFolderName(this.folderName);
        folder.setBelongProject(this.belongProject);
        folder.setParentId(this.parentId);
        return folder;
    }
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

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

    public Long getBelongProject() {
        return belongProject;
    }

    public void setBelongProject(Long belongProject) {
        this.belongProject = belongProject;
    }
}
