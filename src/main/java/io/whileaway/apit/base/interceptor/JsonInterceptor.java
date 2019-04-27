package io.whileaway.apit.base.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class JsonInterceptor implements HandlerInterceptor {

    private final HttpSession session;

    @Autowired
    public JsonInterceptor(HttpSession session) {
        this.session = session;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取路径变量
//        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        String value = (String) pathVariables.get("code");
//        projectService.findByProjectId()
//        throw new CommonException(ControllerEnum.SERVER_ERROR);
        final Optional<Cookie> first = Stream.of(Optional.ofNullable(request.getCookies()).orElseGet(() -> new Cookie[]{}))
                .peek(cookie -> System.out.println(cookie.getName() + ":" + cookie.getValue()))
                .filter(cookie -> "JSESSIONID".equals(cookie.getName()))
                .findFirst();
        System.out.println("SESSION ID:" + session.getId());
        first.ifPresent(cookie -> System.out.println("Find Cookie : " + cookie.getName() + ":" + cookie.getValue()));
        System.out.println(request.getContentType());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {
    }
}
