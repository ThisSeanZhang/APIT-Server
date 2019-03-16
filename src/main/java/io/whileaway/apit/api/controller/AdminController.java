package io.whileaway.apit.api.controller;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.FilterDeveloper;
import io.whileaway.apit.account.request.ModifyDeveloper;
import io.whileaway.apit.account.response.DeveloperIdName;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.api.response.ProjectVO;
import io.whileaway.apit.api.request.FilterProject;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/admin")
public class AdminController {


    private final ProjectService projectService;
    private final DeveloperService developerService;

    @Autowired
    public AdminController(ProjectService projectService, DeveloperService developerService) {
        this.projectService = projectService;
        this.developerService = developerService;
    }

    @GetMapping("/projects")
    public Result<Page<ProjectVO>> getAllProject(FilterProject filterProject, @PageableDefault(sort = { "pid" }) Pageable pageable) {
        Page<ProjectVO> projectVOPage = projectService.adminFilterFind(filterProject, pageable);
        List<Long> ids = projectVOPage.stream().map(ProjectVO::getProjectOwner).collect(Collectors.toList());
        Map<Long, String> developerIds = developerService.findDeveloperByIds(ids).stream().collect(Collectors.toMap(DeveloperIdName::getId, DeveloperIdName::getName));
        List<ProjectVO> collect = projectVOPage.stream().peek(projectVO -> projectVO.injectName(developerIds)).collect(Collectors.toList());
        return ResultUtil.success(
                new PageImpl<>(collect, pageable, projectVOPage.getTotalElements())
        );
    }

    @GetMapping("/developers")
    public Result<Page<Developer>> getAllDeveloper(FilterDeveloper filterDeveloper, @PageableDefault(sort = { "developerId" }) Pageable pageable) {
        return ResultUtil.success(developerService.adminFilterFind(filterDeveloper, pageable));
    }

    @GetMapping("/developers/{did}")
    public Result<Developer> getDeveloper(@PathVariable("did") Long did) {
        return ResultUtil.success(developerService.getDeveloper(did));
    }

    @PutMapping("/developers/{did}")
    public Result editDeveloper(@PathVariable("did") Long did, @RequestBody ModifyDeveloper modifyDeveloper) {
        modifyDeveloper.setDeveloperId(did);
        developerService.adminUpdate(modifyDeveloper);
        return ResultUtil.success();
    }

}
