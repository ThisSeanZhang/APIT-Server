package io.whileaway.apit.api.request;

import javax.validation.constraints.NotNull;

public class LocationRequest {

    private Long belongFolder;
    @NotNull(message="需要指定所属的项目")
    private Long belongProject;

    public Long getBelongFolder() {
        return belongFolder;
    }

    public void setBelongFolder(Long belongFolder) {
        this.belongFolder = belongFolder;
    }

    public Long getBelongProject() {
        return belongProject;
    }

    public void setBelongProject(Long belongProject) {
        this.belongProject = belongProject;
    }

    @Override
    public String toString() {
        return "LocationRequest{" +
                "belongFolder=" + belongFolder +
                ", belongProject=" + belongProject +
                '}';
    }
}
