package io.whileaway.apit.api.interceptor;

import io.whileaway.apit.api.service.APIService;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.base.enums.SessionKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class ProjectInterceptor implements HandlerInterceptor {

    private final ProjectService projectService;
    private final FolderService folderService;
    private final APIService apiService;

    private final HttpSession session;

    @Autowired
    public ProjectInterceptor(ProjectService projectService, FolderService folderService, APIService apiService, HttpSession session) {
        this.projectService = projectService;
        this.folderService = folderService;
        this.apiService = apiService;
        this.session = session;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取路径变量
//        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        String value = (String) pathVariables.get("code");
//        projectService.findByProjectId()
//        throw new CommonException(ControllerEnum.SERVER_ERROR);
//        return true;
        if ( "GET".equals(request.getMethod().toUpperCase()) )
            return true;
        else if (isLoing())
            return true;
        else
            throw new CommonException(ControllerEnum.UNAUTHORIZED);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {

    }

    private boolean isLoing() {
        return Objects.nonNull(session.getAttribute(SessionKeyConstant.CURRENT_DEVELOPER));
    }
}
