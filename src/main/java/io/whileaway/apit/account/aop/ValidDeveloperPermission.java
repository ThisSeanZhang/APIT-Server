package io.whileaway.apit.account.aop;

import io.whileaway.apit.account.annotation.ValidDeveloper;
import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.service.DeveloperService;
import io.whileaway.apit.utils.ValidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ValidDeveloperPermission {

    private final DeveloperService developerService;
    private final ValidUtil validUtil;

    @Autowired
    public ValidDeveloperPermission(DeveloperService developerService, ValidUtil validUtil) {
        this.developerService = developerService;
        this.validUtil = validUtil;
    }

    @Before(value = "@annotation(validDeveloper)", argNames = "joinPoint, validDeveloper")
    public void beforeCheck(JoinPoint joinPoint, ValidDeveloper validDeveloper) {
        System.out.println(joinPoint.getSignature());
        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        Developer developer = validUtil.getCurrentDeveloper();
        Long did = validUtil.getURITempleVariables("did");
        System.out.println("did为" + did);
        // 不是管理员就要进行检查
        if (!developer.getAdmin()) {
            developerService.inspectPermission(developer.getDeveloperId(), did, developerService::isPrivate);
        }
    }
}
