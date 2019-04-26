package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.enums.error.FolderError;
import io.whileaway.apit.api.request.EditAPIResponseExample;
import io.whileaway.apit.api.request.EditAPIResponseParams;
import io.whileaway.apit.api.request.LocationRequest;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.request.CreateAPI;
import io.whileaway.apit.api.service.APIService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.utils.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("projects/{pid}/apis")
public class APIController {

    private final APIService apiService;
    private final ProjectService projectService;
    private final FolderService folderService;
    private final ValidUtil validUtil;

    @Autowired
    public APIController(APIService apiService, ProjectService projectService, FolderService folderService, ValidUtil validUtil) {
        this.apiService = apiService;
        this.projectService = projectService;
        this.folderService = folderService;
        this.validUtil = validUtil;
    }

    @GetMapping("/{aid}")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<API> findById(@PathVariable("aid") Long aid) {
        return ResultUtil.success(apiService.findById(aid));
    }

    @PostMapping
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<API> createAPI(
            @PathVariable("pid") Long pid,
            @Valid @RequestBody CreateAPI createAPI,
            BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        createAPI.setBelongProject(pid);
        return ResultUtil.success(apiService.createAPI(createAPI.covertToAPI()));
    }

    @PutMapping("/{aid}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<API> modifyAPI(
            @PathVariable("aid") Long aid,
            @Valid @RequestBody API updateApi) {
        updateApi.setAid(aid);
        projectService.inspectPermission(
                validUtil.getCurrentDeveloper().getDeveloperId(),
                updateApi.getBelongProject(),
                projectService::checkAllowModifyContent);
        return ResultUtil.success(apiService.updateApi(updateApi));
    }

    @DeleteMapping("/{aid}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<API> delAPI(@PathVariable("aid") Long aid) {
        apiService.delApi(aid);
        return ResultUtil.success();
    }

    @PutMapping("/{aid}/location")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result changeLocation (@PathVariable("aid") Long aid,@RequestBody LocationRequest locationRequest) {
        if (Objects.isNull(aid)) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        System.out.println(locationRequest);
        if (Objects.nonNull(locationRequest.getBelongFolder()))
            folderService.getFolderUncheck(locationRequest.getBelongFolder())
                    .orElseThrow(() -> new CommonException(FolderError.NOT_FOUND));

        projectService.inspectPermission(
                validUtil.getCurrentDeveloper().getDeveloperId(),
                locationRequest.getBelongProject(),
                projectService::checkAllowModifyContent);

        apiService.moveApi(aid, locationRequest);
        return ResultUtil.success();
    }

    @PutMapping("/{aid}/response_example")
    public Result editResponseExample (@PathVariable("aid") Long aid, @RequestBody EditAPIResponseExample editAPIResponseExample) {
        editAPIResponseExample.setAid(aid);
        apiService.updateResponseExample(editAPIResponseExample);
        return  ResultUtil.success();
    }

    @GetMapping("/{aid}/response_example")
    public Result getResponseExample (@PathVariable("aid") Long aid) {
        API api = apiService.getApi(aid);
        return  ResultUtil.success(api.getResponseExample());
    }

    @PutMapping("/{aid}/example_params")
    public Result editResponseExampleParams (@PathVariable("aid") Long aid, @RequestBody EditAPIResponseParams editAPIResponseParams) {
        editAPIResponseParams.setAid(aid);
        apiService.updateResponseParams(editAPIResponseParams);
        return  ResultUtil.success();
    }

    @GetMapping("/{aid}/example_params")
    public Result getResponseExampleParams (@PathVariable("aid") Long aid) {
        API api = apiService.getApi(aid);
        return  ResultUtil.success(api.getExampleParams());
    }
}
