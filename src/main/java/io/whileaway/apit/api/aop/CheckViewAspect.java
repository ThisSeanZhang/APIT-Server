package io.whileaway.apit.api.aop;

import io.whileaway.apit.api.annotation.CheckProjectPermission;
import org.springframework.beans.factory.annotation.Autowired;
import io.whileaway.apit.api.service.ProjectService;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class CheckViewAspect {

    private final ProjectService projectService;

    @Autowired
    public CheckViewAspect(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Before(value = "@annotation(checkProjectPermission)", argNames = "joinPoint, checkProjectPermission")
    public void beforeCheck(JoinPoint joinPoint, CheckProjectPermission checkProjectPermission) {
        System.out.println(joinPoint.getSignature());
        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        switch (checkProjectPermission.value()){
            case VIEW:
                projectService.inspectPermission((HttpServletRequest) joinPoint.getArgs()[0], (Long) joinPoint.getArgs()[1], projectService::checkAllowView);
                break;
            case DELETE:
                projectService.inspectPermission((HttpServletRequest) joinPoint.getArgs()[0], (Long) joinPoint.getArgs()[1], projectService::checkAllowDelete);
                break;
            case MODIFY:
                projectService.inspectPermission((HttpServletRequest) joinPoint.getArgs()[0], (Long) joinPoint.getArgs()[1], projectService::checkAllowModify);
                break;
        }
    }
}
