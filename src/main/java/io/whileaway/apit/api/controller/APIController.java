package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.request.CreateAPI;
import io.whileaway.apit.api.service.APIService;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("projects/{pid}/apis")
public class APIController {

    private final APIService apiService;
    private final ProjectService projectService;

    @Autowired
    public APIController(APIService apiService, ProjectService projectService) {
        this.apiService = apiService;
        this.projectService = projectService;
    }

    @GetMapping("/{aid}")
    public Result<API> findById(HttpServletRequest request, @PathVariable("pid") Long pid, @PathVariable("aid") Long aid) {
        projectService.inspectPermission(request, pid, projectService::checkAllowView);
        return apiService.findById(aid);
    }

    @PostMapping
    public Result<API> createAPI(@Valid @RequestBody CreateAPI createAPI, BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        return apiService.createAPI(createAPI.covertToAPI());
    }

    @PutMapping("/{aid}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<API> createAPI(HttpServletRequest request, @PathVariable("pid") Long pid, @PathVariable("aid") Long aid, @Valid @RequestBody API updateApi) {
//        projectService.inspectPermission(request, pid, projectService::checkAllowModify);
        updateApi.setAid(aid);
        return apiService.updateApi(updateApi);
    }
}
