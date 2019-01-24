package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.base.Result;

public interface DeveloperService {

    Result createDeveloper(Developer developer);

    Developer findByName(String developerName);
}
