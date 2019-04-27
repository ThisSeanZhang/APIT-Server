package io.whileaway.apit.api.aop;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.base.enums.SessionKeyConstant;
import io.whileaway.apit.utils.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import io.whileaway.apit.api.service.ProjectService;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Aspect
public class CheckViewAspect {

    private final ProjectService projectService;
    private final ValidUtil validUtil;

    @Autowired
    public CheckViewAspect(ProjectService projectService, ValidUtil validUtil) {
        this.projectService = projectService;
        this.validUtil = validUtil;
    }

    @Before(value = "@annotation(checkProjectPermission)", argNames = "joinPoint, checkProjectPermission")
    public void beforeCheck(JoinPoint joinPoint, CheckProjectPermission checkProjectPermission) {
        System.out.println(joinPoint.getSignature());
        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        Developer developer = validUtil.getCurrentDeveloper();
        if (developer.getAdmin()) return;
        Long pid = validUtil.getURITempleVariables("pid");
        System.out.println("pid为" + pid);
        switch (checkProjectPermission.value()){
            case VIEW:
                projectService.inspectPermission(developer.getDeveloperId(), pid, projectService::checkAllowView);
                break;
            case DELETE:
                projectService.inspectPermission(developer.getDeveloperId(), pid, projectService::checkAllowModifyProject);
                break;
            case MODIFY:
                projectService.inspectPermission(developer.getDeveloperId(), pid, projectService::checkAllowModifyContent);
                break;
        }
    }
}