package io.whileaway.apit.account.service.impl;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.repository.DeveloperRepository;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.base.*;
import io.whileaway.apit.base.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public Result<Developer> createDeveloper(Developer developer) {
        return ResultUtil.success(ResponseEnum.SUCCESS, developer);
    }

    @Override
    public Developer findByName(String developerName) {
        Optional<Developer> developer = developerRepository.findByDeveloperName(developerName);
        return developer.get();
    }

    public Result<Developer> getResult(Function<Developer,Result<Developer>> function, Developer developer, ResponseEnum responseEnum) {
        return function.apply(developer);
    }
}
