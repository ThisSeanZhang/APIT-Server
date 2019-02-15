package io.whileaway.apit.base.controller;

import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @GetMapping("/checks/connection")
    public Result checkConnection () {
        return ResultUtil.success(ControllerEnum.SUCCESS);
    }
}
