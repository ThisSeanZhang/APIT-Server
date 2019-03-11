package io.whileaway.apit.api.aop;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.base.enums.SessionKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import io.whileaway.apit.api.service.ProjectService;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class CheckViewAspect {

    private final ProjectService projectService;
    private final HttpSession session;
    private final HttpServletRequest request;

    @Autowired
    public CheckViewAspect(ProjectService projectService, HttpSession session, HttpServletRequest request) {
        this.projectService = projectService;
        this.session = session;
        this.request = request;
    }

    @Before(value = "@annotation(checkProjectPermission)", argNames = "joinPoint, checkProjectPermission")
    public void beforeCheck(JoinPoint joinPoint, CheckProjectPermission checkProjectPermission) {
        System.out.println(joinPoint.getSignature());
        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        Developer developer = getCurrentDeveloper();
        Long pid = getURITempleVariables("pid");
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

    public void pringSessionAndRequestInfo () {
        System.out.println("session id" + session.getId());
        System.out.println("request" + request.getMethod() + " " + request.getParameterMap().entrySet()
                .stream()
                .map( e-> e.getKey() + ":"+ Arrays.toString(e.getValue()))
                .collect(Collectors.joining(",")));
        System.out.println("request:" + request.getContextPath() +":"+ request.getServletPath() + ":" +  request.getPathInfo() +":" + request.getRequestURI());

    }

    private Developer getCurrentDeveloper () {
        Object currentDeveloper = session.getAttribute(SessionKeyConstant.CURRENT_DEVELOPER);
        if (Objects.nonNull(currentDeveloper) && currentDeveloper instanceof Developer) {
            return (Developer) currentDeveloper;
        }
        return new Developer();
    }

    private Long getURITempleVariables (String key) {
        Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Object rawValue = attribute.get(key);
        if (Objects.isNull(rawValue) || !(rawValue instanceof String))
            return null;
        String value = (String) rawValue;
        return value.matches("^[0-9]+$")? Long.valueOf(value) : null ;
    }

}