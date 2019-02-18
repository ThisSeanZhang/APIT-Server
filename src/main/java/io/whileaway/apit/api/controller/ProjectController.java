package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.request.CreateProject;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public Result<List<Node>> getFirstLayerContent (HttpServletRequest request, @PathVariable("projectId") Long belongProject, @RequestParam("ownerId") Long ownerId) {
        if (belongProject == null || ownerId == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        projectService.inspectPermission(request, belongProject);
        return projectService.firstLayerContent(belongProject, ownerId);
    }

    @PostMapping
    public Result<Project> createProject (CreateProject createProject, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        return projectService.createProject(createProject.convertToProject());
    }

    @GetMapping("/{id}")
    public Result<Project> findProjectById (HttpServletRequest request, @PathVariable("id") Long pid) {
        projectService.inspectPermission(request, pid);
        return ResultUtil.success(ControllerEnum.SUCCESS, projectService.getProject(pid));
    }
}
