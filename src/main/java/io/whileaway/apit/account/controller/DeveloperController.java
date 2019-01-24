package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.request.CreateDeveloper;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping
    public Result createDeveloper(@Valid  @RequestBody CreateDeveloper createDeveloper, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        return developerService.createDeveloper(createDeveloper.convertToDeveloper());
    }

}
