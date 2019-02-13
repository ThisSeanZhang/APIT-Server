package io.whileaway.apit.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;
    private String label;
    private Boolean leaf;
    private Integer type;
    private Long contain;
    private Long parentId;
    private Long ownerId;
    private Long belongProject;

    public Node () {}
    public Node(Folder folder) {
        this.label = folder.getFolderName();
        this.leaf = false;
        this.type = 0;
        this.contain = folder.getFid();
        this.parentId = folder.getParentId();
        this.ownerId = folder.getFolderOwnerId();
        this.belongProject = folder.getBelongProject();
    }

    public Node(API api) {
        this.label = api.getApiName();
        this.leaf = true;
        this.type = 1;
        this.contain = api.getAid();
        this.parentId = api.getBelongNode();
        this.ownerId = api.getApiOwner();
        this.belongProject = api.getBelongProject();
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

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
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
