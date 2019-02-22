package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.CreateSession;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.utils.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private final DeveloperService developerService;

    @Autowired
    public SessionServiceImpl(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @Override
    public Result<Developer> createSession(CreateSession createSession) {
        List<Developer> developers = developerService.findByEmailOrDeveloperName(createSession.getDeveloperNameOrEmail(), createSession.getDeveloperNameOrEmail());

        List<Developer> targets = developers.stream()
                .filter( developer ->
                        developer.getDeveloperPass().equals(
                                Crypto.cryptoPass(createSession.getDeveloperPass(), developer.getSalt())
                        )
                )
                .collect(Collectors.toList());
        if (targets.isEmpty()) throw new CommonException(ControllerEnum.NOT_FOUND);
        if (targets.size() > 1) throw new CommonException(ControllerEnum.SERVER_ERROR);
        return ResultUtil.success(ControllerEnum.SUCCESS, targets.get(0));
    }
}
