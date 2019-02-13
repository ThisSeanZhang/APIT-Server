package io.whileaway.apit.api.request;

public class FilterNode {

    private String label;
    private Long parentId;
    private Long ownerId;
    private Long belongProject;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    @Override
    public String toString() {
        return "FilterNode{" +
                "label='" + label + '\'' +
                ", parentId=" + parentId +
                ", ownerId=" + ownerId +
                ", belongProject=" + belongProject +
                '}';
    }
}
