package io.whileaway.apit.api.response;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.entity.Folder;

public class Node {

    private String nid;
    private String label;
    private Boolean leaf;
    private Integer type;
    private Long contain;
    private Long parentId;
    private Long ownerId;
    private Long belongProject;

    public Node(Folder folder) {
        this.nid = "folder-" + folder.getFid().toString();
        this.label = folder.getFolderName();
        this.leaf = false;
        this.type = 0;
        this.contain = folder.getFid();
        this.parentId = folder.getParentId();
        this.ownerId = folder.getFolderOwnerId();
        this.belongProject = folder.getBelongProject();
    }

    public Node(API api) {
        this.nid = "api-" + api.getAid().toString();
        this.label = api.getApiName();
        this.leaf = true;
        this.type = 1;
        this.contain = api.getAid();
        this.parentId = api.getBelongFolder();
        this.ownerId = api.getApiOwner();
        this.belongProject = api.getBelongProject();
    }

    public Node(String label, Boolean leaf, Integer type, Long contain, Long parentId, Long ownerId, Long belongProject) {
        this.label = label;
        this.leaf = leaf;
        this.type = type;
        this.contain = contain;
        this.parentId = parentId;
        this.ownerId = ownerId;
        this.belongProject = belongProject;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getBelongProject() {
        return belongProject;
    }

    public void setBelongProject(Long belongProject) {
        this.belongProject = belongProject;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getContain() {
        return contain;
    }

    public void setContain(Long contain) {
        this.contain = contain;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }
}
