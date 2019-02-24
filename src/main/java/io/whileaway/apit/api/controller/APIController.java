package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.request.CreateAPI;
import io.whileaway.apit.api.service.APIService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("projects/{pid}/apis")
public class APIController {

    private final APIService apiService;

    @Autowired
    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/{aid}")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<API> findById(@PathVariable("aid") Long aid) {
        return apiService.findById(aid);
    }

    @PostMapping
    public Result<API> createAPI(@Valid @RequestBody CreateAPI createAPI, BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        return apiService.createAPI(createAPI.covertToAPI());
    }

    @PutMapping("/{aid}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<API> createAPI(@PathVariable("aid") Long aid, @Valid @RequestBody API updateApi) {
        updateApi.setAid(aid);
        return apiService.updateApi(updateApi);
    }

    @DeleteMapping("/{aid}")
    @CheckProjectPermission(PermissionType.DELETE)
    public Result<API> delAPI(@PathVariable("aid") Long aid) {
        return apiService.delApi(aid);
    }

}
