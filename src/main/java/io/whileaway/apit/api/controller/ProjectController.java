package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
