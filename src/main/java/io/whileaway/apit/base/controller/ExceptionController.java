package io.whileaway.apit.base.controller;

import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ExceptionController {

    @Autowired
    private ExceptionService exceptionService;

    @GetMapping("/error")
    public Result error(){
        exceptionService.throwTheException();
        return ResultUtil.success(200, "成功");
    }
}
