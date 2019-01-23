package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.CreateDeveloper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @PostMapping()
    public Developer createDevelper(@RequestBody CreateDeveloper createDeveloper){
        return createDeveloper.convertToDeveloper();
    }

}
