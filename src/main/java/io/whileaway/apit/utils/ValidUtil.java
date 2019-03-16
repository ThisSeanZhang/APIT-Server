package io.whileaway.apit.utils;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.base.enums.SessionKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ValidUtil {

    private final HttpSession session;
    private final HttpServletRequest request;

    @Autowired
    public ValidUtil(HttpSession session, HttpServletRequest request) {
        this.session = session;
        this.request = request;
    }

    public Developer getCurrentDeveloper () {
        Object currentDeveloper = session.getAttribute(SessionKeyConstant.CURRENT_DEVELOPER);
        if (Objects.nonNull(currentDeveloper) && currentDeveloper instanceof Developer) {
            return (Developer) currentDeveloper;
        }
        throw new CommonException(ControllerEnum.UNAUTHORIZED);
    }

    public Long getURITempleVariables (String key) {
        Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Object rawValue = attribute.get(key);
        if (Objects.isNull(rawValue) || !(rawValue instanceof String))
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        String value = (String) rawValue;
        if ( value.matches("^[0-9]+$"))
            return  Long.valueOf(value);
        throw new CommonException(ControllerEnum.PARAMETER_ERROR);
    }

    public void pringSessionAndRequestInfo () {
        System.out.println("session id" + session.getId());
        System.out.println("request" + request.getMethod() + " " + request.getParameterMap().entrySet()
                .stream()
                .map( e-> e.getKey() + ":"+ Arrays.toString(e.getValue()))
                .collect(Collectors.joining(",")));
        System.out.println("request:" + request.getContextPath() +":"+ request.getServletPath() + ":" +  request.getPathInfo() +":" + request.getRequestURI());

    }
}
