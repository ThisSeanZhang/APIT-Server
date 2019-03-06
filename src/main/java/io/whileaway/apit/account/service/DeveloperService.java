package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.response.DeveloperIdName;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    Optional<Developer> createDeveloper(Developer developer);

    Optional<String> emailIsExists(String email);

    Optional<String> nameIsExists(String name);

    Optional<List<DeveloperIdName>> findByNameOrEmailLike(String key);

    List<Developer> findByEmailOrDeveloperName(String email, String developerName);

    List<DeveloperIdName> findDeveloperByIds (List<Long> ids);
}
