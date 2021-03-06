package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.FilterDeveloper;
import io.whileaway.apit.account.request.ModifyDeveloper;
import io.whileaway.apit.account.response.DeveloperIdName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public interface DeveloperService {

    Optional<Developer> createDeveloper(Developer developer);

    Optional<String> emailIsExists(String email);

    Optional<String> nameIsExists(String name);

    Optional<List<DeveloperIdName>> findByNameOrEmailLike(String key);

    List<Developer> findByEmailOrDeveloperName(String email, String developerName);

    List<DeveloperIdName> findDeveloperByIds (List<Long> ids);

    Map<Long, String> findDeveloperByIdsToMap (List<Long> ids);

    Page<Developer> adminFilterFind(FilterDeveloper filterDeveloper, Pageable pageable);

    boolean inspectPermission(Long developerId, Long did, BiFunction<Developer, Long, Boolean> check);

    boolean isPrivate(Developer project, Long developerId);

    Developer getDeveloper(Long did);

    void adminUpdate(ModifyDeveloper modifyDeveloper);
}
