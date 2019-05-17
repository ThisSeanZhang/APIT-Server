package io.whileaway.apit.api.request;

import io.whileaway.apit.api.entity.Folder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ModifyFolder {
    private Long fid;
    @Length(max = 8, min = 1, message = "项目名称在1-8个字符之间")
    private String folderName;
    private Long parentId;
    @NotNull(message = "所属的项目不能为空")
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
