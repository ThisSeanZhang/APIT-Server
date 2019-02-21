package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.request.CreateDeveloper;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping
    public Result createDeveloper(@Valid  @RequestBody CreateDeveloper createDeveloper, BindingResult bindingResult){
        ResultUtil.inspect(bindingResult);
        return developerService.createDeveloper(createDeveloper.convertToDeveloper());
    }

    @GetMapping("/email/{email}")
    public Result emailIsExists(@PathVariable("email") String email){
        return developerService.emailIsExists(email);
    }

    /**
     * https://www.cnblogs.com/leechenxiang/p/6181449.html
     * 后面记得加上限制，访问次数限制
     * @param developerName
     * @return
     */
    @GetMapping("/developer-name/{developerName}")
    public Result developerNameIsExists(@PathVariable("developerName") String developerName){
        return developerService.nameIsExists(developerName);
    }

}
