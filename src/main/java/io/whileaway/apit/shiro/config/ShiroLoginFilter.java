package io.whileaway.apit.shiro.config;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class ShiroLoginFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        if (this.isLoginRequest(request, response)) {
//            if (this.isLoginSubmission(request, response)) {
//                return this.executeLogin(request, response);
//            } else {
//                return true;
//            }
//        } else {
//            return false;
//        }
//        return super.onAccessDenied(request, response);
        return true;
    }
}
