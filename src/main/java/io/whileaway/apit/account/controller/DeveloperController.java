package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.request.CreateDeveloper;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping
    public Result createDeveloper(@RequestBody CreateDeveloper createDeveloper){
        return developerService.createDeveloper(createDeveloper.convertToDeveloper());
    }

}
