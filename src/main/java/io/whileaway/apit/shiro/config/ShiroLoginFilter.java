package io.whileaway.apit.shiro.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.shiro.enums.error.AuthEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class ShiroLoginFilter extends FormAuthenticationFilter {

    private final ObjectMapper objectMapper;

    @Autowired
    public ShiroLoginFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isLoginRequest(request, response)) {
            return true;
        } else {
            if( request instanceof HttpServletRequest ) {
                HttpServletResponse r = (HttpServletResponse) response;
                r.setCharacterEncoding("utf-8");
                r.setStatus(AuthEnum.NOT_ALLOW.getCode());
                Result error = ResultUtil.error(AuthEnum.NOT_ALLOW.getCode(), AuthEnum.NOT_ALLOW.getMessage());
                PrintWriter writer = r.getWriter();
                writer.print(objectMapper.writeValueAsString(error));
                writer.close();
            }
            return false;
        }
    }
}
