package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.service.APIService;
import io.whileaway.apit.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis")
public class APIController {

    private final APIService apiService;

    @Autowired
    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/{aid}")
    public Result<API> findById(@PathVariable("aid") Long aid) {
        return apiService.findById(aid);
    }
}
