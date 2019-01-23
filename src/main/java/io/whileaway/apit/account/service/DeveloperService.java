package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;

public interface DeveloperService {

    void createDeveloper(Developer developer);

    Developer findByName(String developerName);
}
