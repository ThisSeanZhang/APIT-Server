package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.enums.error.DeveloperError;
import io.whileaway.apit.account.request.CreateDeveloper;
import io.whileaway.apit.account.response.DeveloperIdName;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public Result<Developer> createDeveloper(@Valid  @RequestBody CreateDeveloper createDeveloper, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        Optional<Developer> developer = developerService.createDeveloper(createDeveloper.convertToDeveloper());
        return ResultUtil.success(developer.orElseThrow(() -> new CommonException(DeveloperError.INSUFFICIENT_STORAGE)));
    }

    @GetMapping("/email/{email}")
    public Result emailIsExists(@PathVariable("email") String email){
        Optional<String> s = developerService.emailIsExists(email);
        if (s.isPresent()) throw new CommonException(DeveloperError.NAME_CONFLICT);
        return ResultUtil.success();
    }

    @GetMapping("/developer-name/{developerName}")
    public Result developerNameIsExists(@PathVariable("developerName") String developerName){
        Optional<String> s = developerService.nameIsExists(developerName);
        if (s.isPresent()) throw new CommonException(DeveloperError.NAME_CONFLICT);
        return ResultUtil.success();
    }

    @GetMapping("/developer-name-email-like/{key}")
    public Result<List<DeveloperIdName>> findDeveloperByNameLike(@PathVariable("key") String key){
        Optional<List<DeveloperIdName>> byNameOrEmailLike = developerService.findByNameOrEmailLike(key);
        if (byNameOrEmailLike.isEmpty() || byNameOrEmailLike.get().isEmpty())
            throw new CommonException(DeveloperError.NOT_FOUND);
        return ResultUtil.success();
    }

    @GetMapping("/{did}/projects")
    public Result<List<Project>> findDevelopersAllProjects(@PathVariable("did") Long did){
        return projectService.getProjectsByOwnerId(did);
    }
}
