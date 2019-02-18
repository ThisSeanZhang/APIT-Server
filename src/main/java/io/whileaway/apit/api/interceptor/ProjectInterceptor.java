//package io.whileaway.apit.api.interceptor;
//
//import io.whileaway.apit.api.service.ProjectService;
//import io.whileaway.apit.base.CommonException;
//import io.whileaway.apit.base.enums.ControllerEnum;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.HandlerMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
//@Component
//public class ProjectInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private ProjectService projectService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        String value = (String) pathVariables.get("code");
////        projectService.findByProjectId()
//        throw new CommonException(ControllerEnum.SERVER_ERROR);
////        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
