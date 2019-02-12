package io.whileaway.apit.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;
    private String folderName;
    private Long parentId;
    private Long folderOwnerId;
    private Long belongProject;

    public Folder() { }
    public Folder(String folderName, Long folderOwnerId, Long belongProject) {
        this.folderName = folderName;
        this.folderOwnerId = folderOwnerId;
        this.belongProject = belongProject;
    }

    public Folder(String folderName, Long parentId, Long folderOwnerId, Long belongProject) {
        this.folderName = folderName;
        this.parentId = parentId;
        this.folderOwnerId = folderOwnerId;
        this.belongProject = belongProject;
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

    public Long getFolderOwnerId() {
        return folderOwnerId;
    }

    public void setFolderOwnerId(Long folderOwnerId) {
        this.folderOwnerId = folderOwnerId;
    }
}
