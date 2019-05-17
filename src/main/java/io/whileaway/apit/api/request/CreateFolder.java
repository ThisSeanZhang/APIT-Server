package io.whileaway.apit.api.request;

import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.enums.StatusDict;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateFolder {

    @Length(max = 8, min = 1, message = "文件夹名称在1-8个字符之间")
    private String folderName;
    private Long parentId;
    private Long folderOwnerId;
    private Long belongProject;

    public Folder convertToFolder() {
        Folder folder = new Folder();
        folder.setFolderName(this.folderName);
        folder.setBelongProject(this.belongProject);
        folder.setParentId(this.parentId);
        folder.setFolderOwnerId(this.folderOwnerId);
        folder.setStatus(StatusDict.NORMAL.getCode());
        return folder;
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
