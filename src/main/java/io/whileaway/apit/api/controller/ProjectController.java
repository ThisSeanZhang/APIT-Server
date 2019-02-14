package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/owner/{id}")
    public Result<List<Project>> getProjectsByOwnerId (@PathVariable("id") Long id) {
        return projectService.getProjectsByOwnerId(id);
    }

    @GetMapping("/{projectId}/content/first-layer")
    public Result<List<Node>> getFirstLayerContent (@PathVariable("projectId") Long belongProject, @RequestParam("ownerId") Long ownerId) {
        if (belongProject == null || ownerId == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return projectService.firstLayerContent(belongProject, ownerId);
    }

}
