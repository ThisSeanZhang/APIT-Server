package io.whileaway.apit.api.controller;

import io.whileaway.apit.account.response.DeveloperIdName;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.base.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.request.CreateProject;
import io.whileaway.apit.api.request.FilterProject;
import io.whileaway.apit.api.request.ModifyProject;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.response.ProjectVO;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

//    @GetMapping("/owner/{did}")
//    public Result<List<Project>> getProjectsByOwnerId (@PathVariable("did") Long id) {
//        List<Project> projectsByOwnerId = projectService.getProjectsByOwnerId(id);
//        return ResultUtil.success(projectsByOwnerId);
//    }

    @GetMapping("/isOvert")
    public Result<Page<ProjectVO>> getIsOvertProject (Pageable pageable) {
        FilterProject filterProject = new FilterProject();
        filterProject.setOvert(true);
        Page<ProjectVO> projectVOPage = projectService.adminFilterFind(filterProject, pageable);
        List<Long> ids = projectVOPage.stream().map(ProjectVO::getProjectOwner).collect(Collectors.toList());
        Map<Long, String> developerIds = developerService.findDeveloperByIdsToMap(ids);
        List<ProjectVO> collect = projectVOPage.stream().peek(projectVO -> projectVO.injectName(developerIds)).collect(Collectors.toList());
        return ResultUtil.success(
                new PageImpl<>(collect, pageable, projectVOPage.getTotalElements())
        );
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}/content/first_layer")
    public Result<List<Node>> getFirstLayerContent (@PathVariable("pid") Long pid) {
        if (pid == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return ResultUtil.checkList(projectService.firstLayerContent(pid), new CommonException(ControllerEnum.NOT_FOUND));
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}/folders/first_layer")
    public Result<List<Node>> getFirstLayerFolder (@PathVariable("pid") Long pid) {
        if (pid == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return ResultUtil.checkList(projectService.firstLayerFolder(pid), new CommonException(ControllerEnum.NOT_FOUND));
    }

//    @PostMapping
//    public Result<Project> createProject (@Valid @RequestBody CreateProject createProject, BindingResult bindingResult){
//        ResultUtil.inspect(bindingResult);
//        return projectService.createProject(createProject.convertToProject());
//    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}")
    public Result<Project> findProjectById (@PathVariable("pid") Long pid) {
        return ResultUtil.success(ControllerEnum.SUCCESS, projectService.getProject(pid));
    }

    @PutMapping("/{pid}")
    @CheckProjectPermission(PermissionType.DELETE)
    public Result<Project> modifyProjectById (@PathVariable("pid") Long pid, @Valid @RequestBody ModifyProject modifyProject, BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        modifyProject.setPid(pid);
        return ResultUtil.success(projectService.modifyProject(modifyProject));
    }

    @DeleteMapping("/{pid}")
    @CheckProjectPermission(PermissionType.DELETE)
    public Result<Project> deleteProjectById (@PathVariable("pid") Long pid) {
        projectService.deleteProject(pid);
        return ResultUtil.success();
    }

    @CheckProjectPermission(PermissionType.VIEW)
    @GetMapping("/{pid}/whoJoins")
    public Result<List<DeveloperIdName>> getWhoJoins (@PathVariable("pid") Long pid) {
        if (pid == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        List<DeveloperIdName> developerByIds = developerService.findDeveloperByIds(projectService.getWhoJoins(pid));
        return ResultUtil.success(developerByIds);
    }
}
