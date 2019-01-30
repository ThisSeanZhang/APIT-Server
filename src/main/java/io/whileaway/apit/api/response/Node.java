package io.whileaway.apit.api.response;

import io.whileaway.apit.api.entity.Folder;

public class Node {

    private Long nid;
    private String label;
    private Boolean leaf;

    public Node(Folder folder) {
        this.nid = folder.getFid();
        this.label = folder.getFolderName();
        this.leaf = false;
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
