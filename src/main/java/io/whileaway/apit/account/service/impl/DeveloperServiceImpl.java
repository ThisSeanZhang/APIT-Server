package io.whileaway.apit.account.service.impl;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.repository.DeveloperRepository;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.base.*;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.base.enums.ResponseEnum;
import io.whileaway.apit.base.trial.Biss;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public Result<Developer> createDeveloper(Developer developer) {
        if (StringUtils.anyIsEmptyOrBlank(developer.getDeveloperName(), developer.getDeveloperPass(), developer.getEmail())) {
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success(ControllerEnum.SUCCESS, developerRepository.save(developer));
    }

    @Override
    public Result<String> emailIsExists(String email) {
        return new Biss<String, Developer, String>(email)
                .inspectParam(StringUtils::isEmptyOrBlank)
                .findInDB(developerRepository::findByEmail)
                .convert(Developer::getDeveloperName);
    }

    @Override
    public Result<String> nameIsExists(String name) {
        return new Biss<String, Developer, String>(name)
                .inspectParam(StringUtils::isEmptyOrBlank)
                .findInDB(developerRepository::findByDeveloperName)
                .convert(Developer::getDeveloperName);

//        return new BissInspecter<String, Developer, String>(name)
//                .inspect(StringUtils::isEmptyOrBlank)
//                .findInDB(developerRepository::findByDeveloperName)
//                .convert(Developer::getDeveloperName);
    }

    @Override
    public Developer findByName(String developerName) {
        Optional<Developer> developer = developerRepository.findByDeveloperName(developerName);
        if (developer.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return developer.get();
    }

    @Override
    public Developer findByEmail(String email) {
        Optional<Developer> developer = developerRepository.findByEmail(email);
        if (developer.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return developer.get();
    }

    @Override
    public List<Developer> findByEmailOrDeveloperName(String email, String developerName) {
        Optional<List<Developer>> primitive = developerRepository.findByEmailOrDeveloperName(email, developerName);
        if (primitive.isEmpty() || primitive.get().isEmpty()) throw new CommonException(ControllerEnum.NOT_FOUND);
        return primitive.get();
    }

    public Result<Developer> getResult(Function<Developer,Result<Developer>> function, Developer developer, ResponseEnum responseEnum) {
        return function.apply(developer);
    }

    /**
     * 过渡的写法
     * @param args
     * @param isNull
     * @param findInDB
     * @param convert
     * @param <A>
     * @param <B>
     * @param <C>
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
