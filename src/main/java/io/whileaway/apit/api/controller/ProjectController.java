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
    public Result<List<Project>> getProjectsByOwnerId (HttpServletRequest request, @PathVariable("id") Long id) {
        projectService.inspectPermission(request, id, projectService::checkAllowView);
        return projectService.getProjectsByOwnerId(id);
    }

    @GetMapping("/{projectId}/content/first-layer")
    public Result<List<Node>> getFirstLayerContent (HttpServletRequest request, @PathVariable("projectId") Long belongProject) {
        if (belongProject == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        projectService.inspectPermission(request, belongProject, projectService::checkAllowView);
        return projectService.firstLayerContent(belongProject);
    }

    @PostMapping
    public Result<Project> createProject (CreateProject createProject, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        return projectService.createProject(createProject.convertToProject());
    }

    @GetMapping("/{id}")
    public Result<Project> findProjectById (HttpServletRequest request, @PathVariable("id") Long pid) {
        projectService.inspectPermission(request, pid, projectService::checkAllowView);
        return ResultUtil.success(ControllerEnum.SUCCESS, projectService.getProject(pid));
    }
}
