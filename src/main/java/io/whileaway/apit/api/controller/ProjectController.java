package io.whileaway.apit.api.controller;

import io.whileaway.apit.account.response.DeveloperIdName;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.api.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.request.CreateProject;
import io.whileaway.apit.api.request.ModifyProject;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final DeveloperService developerService;

    @Autowired
    public ProjectController(ProjectService projectService, DeveloperService developerService) {
        this.projectService = projectService;
        this.developerService = developerService;
    }

    @GetMapping("/owner/{id}")
    public Result<List<Project>> getProjectsByOwnerId (@PathVariable("id") Long id) {
        return projectService.getProjectsByOwnerId(id);
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}/content/first-layer")
    public Result<List<Node>> getFirstLayerContent (@PathVariable("pid") Long pid) {
        if (pid == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return projectService.firstLayerContent(pid);
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}/folders/first-layer")
    public Result<List<Node>> getFirstLayerFolder (@PathVariable("pid") Long pid) {
        if (pid == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return projectService.firstLayerFolder(pid);
    }

    @PostMapping
    public Result<Project> createProject (@Valid @RequestBody CreateProject createProject, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        return projectService.createProject(createProject.convertToProject());
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}")
    public Result<Project> findProjectById (@PathVariable("pid") Long pid) {
        return ResultUtil.success(ControllerEnum.SUCCESS, projectService.getProject(pid));
    }

    @PutMapping("/{pid}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<Project> modifyProjectById (@PathVariable("pid") Long pid, @Valid @RequestBody ModifyProject modifyProject, BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        modifyProject.setPid(pid);
        return ResultUtil.success(projectService.modifyProject(modifyProject));
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}/whoJoins")
    public Result<List<DeveloperIdName>> getWhoJoins (@PathVariable("pid") Long pid) {
        if (pid == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        List<DeveloperIdName> developerByIds = developerService.findDeveloperByIds(projectService.getWhoJoins(pid));
        return ResultUtil.success(developerByIds);
    }
}
