package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
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
//        projectService.inspectPermission(request, id, projectService::checkAllowView);
        return projectService.getProjectsByOwnerId(id);
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}/content/first-layer")
    public Result<List<Node>> getFirstLayerContent (HttpServletRequest request, @PathVariable("pid") Long pid) {
        if (pid == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
//        projectService.inspectPermission(request, pid, projectService::checkAllowView);
        return projectService.firstLayerContent(pid);
    }

    @PostMapping
    public Result<Project> createProject (CreateProject createProject, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        return projectService.createProject(createProject.convertToProject());
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{id}")
    public Result<Project> findProjectById (HttpServletRequest request, @PathVariable("id") Long pid) {
//        projectService.inspectPermission(request, pid, projectService::checkAllowView);
        return ResultUtil.success(ControllerEnum.SUCCESS, projectService.getProject(pid));
    }

    @PutMapping("/{id}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<Project> modifyProjectById (HttpServletRequest request, @PathVariable("id") Long pid) {
//        projectService.inspectPermission(request, pid, projectService::checkAllowModify);
        return ResultUtil.success(ControllerEnum.SUCCESS, projectService.getProject(pid));
    }
}
