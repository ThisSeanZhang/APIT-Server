package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.repository.DeveloperRepository;
import io.whileaway.apit.account.request.FilterDeveloper;
import io.whileaway.apit.account.response.DeveloperIdName;
import io.whileaway.apit.account.specs.DeveloperSpec;
import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.*;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.base.enums.ResponseEnum;
import io.whileaway.apit.base.trial.Biss;
import io.whileaway.apit.utils.Crypto;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;
    private final FolderService folderService;
    private final ProjectService projectService;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository, FolderService folderService, ProjectService projectService) {
        this.developerRepository = developerRepository;
        this.folderService = folderService;
        this.projectService = projectService;
    }

    @Override
    public Optional<Developer> createDeveloper(Developer developer) {
        if (StringUtils.anyIsEmptyOrBlank(developer.getDeveloperName(), developer.getDeveloperPass(), developer.getEmail())) {
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        }
        Crypto.cryptoDeveloperPass(developer);
        Developer data = developerRepository.save(developer);
        Project project = projectService.createProject(new Project("默认项目", data.getDeveloperId())).getData();
        Folder folder = folderService.createFolder(new Folder("默认文件夹", data.getDeveloperId(), project.getPid())).getData();
        data.setDefaultProject(project.getPid());
        data.setDefaultFolder(folder.getFid());
        return Optional.ofNullable(developerRepository.save(data));
    }

    @Override
    public Optional<String> emailIsExists(String email) {
        return new Biss<String, Developer, String>(email)
                .inspectParam(StringUtils::isEmptyOrBlank)
                .findInDB(developerRepository::findByEmail)
                .toOptional(Developer::getDeveloperName);
    }

    @Override
    public Optional<String> nameIsExists(String name) {
        return new Biss<String, Developer, String>(name)
                .inspectParam(StringUtils::isEmptyOrBlank)
                .findInDB(developerRepository::findByDeveloperName)
                .toOptional(Developer::getDeveloperName);

//        return new BissInspecter<String, Developer, String>(name)
//                .inspect(StringUtils::isEmptyOrBlank)
//                .findInDB(developerRepository::findByDeveloperName)
//                .convert(Developer::getDeveloperName);
    }

    @Override
    public List<Developer> findByEmailOrDeveloperName(String email, String developerName) {
        Optional<List<Developer>> primitive = developerRepository.findByEmailOrDeveloperName(email, developerName);
        if (primitive.isEmpty() || primitive.get().isEmpty()) throw new CommonException(ControllerEnum.NOT_FOUND);
        return primitive.get();
    }

    @Override
    public Optional<List<DeveloperIdName>> findByNameOrEmailLike(String key) {
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(key);
        Optional<List<Developer>> developers = developerRepository.findByEmailLikeOrDeveloperNameLike(
                "%"+ key +"%",
                "%"+ key +"%",
                pageable);
        if (developers.isEmpty())
            throw new CommonException(ControllerEnum.NOT_FOUND);
        return Optional.of(developers.get().stream().map(DeveloperIdName::new).collect(Collectors.toList()));
    }

    @Override
    public List<DeveloperIdName> findDeveloperByIds(List<Long> ids) {
        return Optional.of(developerRepository.findAllById(ids))
                .orElse(List.of())
                .stream()
                .map(DeveloperIdName::new)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, String> findDeveloperByIdsToMap(List<Long> ids) {
        final List<DeveloperIdName> byIds = findDeveloperByIds(ids);
        return byIds.stream().collect(Collectors.toMap(DeveloperIdName::getId, DeveloperIdName::getName));
    }

    @Override
    public Page<Developer> adminFilterFind(FilterDeveloper filterDeveloper, Pageable pageable) {
        return new PageSpec<Developer, Developer>(pageable)
                .appendCondition(DeveloperSpec.likeDeveloperName(filterDeveloper::getDeveloperName))
                .appendCondition(DeveloperSpec.likeEmail(filterDeveloper::getEmail))
                .appendCondition(DeveloperSpec.admin(filterDeveloper::getAdmin))
                .findInDB(developerRepository::findAll)
                .nothing();
    }

    public Result<Developer> getResult(Function<Developer,Result<Developer>> function, Developer developer, ResponseEnum responseEnum) {
        return function.apply(developer);
    }

    /**
     * 过渡的写法
     * @return return whatIsExists(email,
     *                 StringUtils::isEmptyOrBlank,
     *                 developerRepository::findByEmail ,
     *                 Developer::getEmail);
     */
    private static<A, B, C> Result<C> whatIsExists(A args, Predicate<A> isNull, Function<A, Optional<B>> findInDB, Function<B, C> convert) {
        if (isNull.test(args)) {
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        }
        Optional<B> targetOP = findInDB.apply(args);
        if (targetOP.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        B target = targetOP.get();
        return ResultUtil.success(ControllerEnum.SUCCESS, convert.apply(target));
    }
}
