package io.whileaway.apit.account.service.impl;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.CreateSession;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.account.service.SessionService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private DeveloperService developerService;

    @Override
    public Result<Developer> createSession(CreateSession createSession) {
        List<Developer> developers = developerService.findByEmailOrDeveloperName(createSession.getDeveloperNameOrEmail(), createSession.getDeveloperNameOrEmail());
        List<Developer> targets = developers.stream()
                .filter( developer -> developer.getDeveloperPass().equals(createSession.getDeveloperPass()) )
                .collect(Collectors.toList());
        if (targets.isEmpty()) throw new CommonException(ControllerEnum.NOT_FOUND);
        if (targets.size() > 1) throw new CommonException(ControllerEnum.SERVER_ERROR);
        return ResultUtil.success(ControllerEnum.SUCCESS, targets.get(0));
    }
}
