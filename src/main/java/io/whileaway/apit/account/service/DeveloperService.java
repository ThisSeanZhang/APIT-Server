package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.base.Result;

import java.util.List;

public interface DeveloperService {

    Result<Developer> createDeveloper(Developer developer);

    Result<String> emailIsExists(String email);

    Result<String> nameIsExists(String name);

    Developer findByName(String developerName);

    Developer findByEmail(String developerName);

    List<Developer> findByEmailOrDeveloperName(String email, String developerName);
}
