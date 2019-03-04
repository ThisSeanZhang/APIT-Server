package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.CreateDeveloper;
import io.whileaway.apit.account.response.DeveloperIdName;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;
    private final ProjectService projectService;

    @Autowired
    public DeveloperController(DeveloperService developerService, ProjectService projectService) {
        this.developerService = developerService;
        this.projectService = projectService;
    }

    @PostMapping
    public Result createDeveloper(@Valid  @RequestBody CreateDeveloper createDeveloper, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        return developerService.createDeveloper(createDeveloper.convertToDeveloper());
    }

    @GetMapping("/email/{email}")
    public Result emailIsExists(@PathVariable("email") String email){
        return developerService.emailIsExists(email);
    }

    @GetMapping("/developer-name/{developerName}")
    public Result developerNameIsExists(@PathVariable("developerName") String developerName){
        return developerService.nameIsExists(developerName);
    }

    @GetMapping("/developer-name-email-like/{key}")
    public Result<List<DeveloperIdName>> findDeveloperByNameLike(@PathVariable("key") String key){
        return developerService.findByNameOrEmailLike(key);
    }

    @GetMapping("/{did}/projects")
    public Result<List<Project>> findDevelopersAllProjects(@PathVariable("did") Long did){
        return projectService.getProjectsByOwnerId(did);
    }
}
