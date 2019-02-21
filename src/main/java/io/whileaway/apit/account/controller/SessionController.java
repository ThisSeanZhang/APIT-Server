package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.CreateSession;
import io.whileaway.apit.account.service.SessionService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping()
    public Result<Developer> createSession(HttpServletRequest httpServletRequest, @Valid @RequestBody CreateSession createSession, BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        Result<Developer> result = sessionService.createSession(createSession);

        httpServletRequest.getSession().setAttribute("currentDeveloper", result.getData());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(createSession.getDeveloperNameOrEmail(), createSession.getDeveloperPass());
        subject.login(token);
        System.out.println("当前的Subject认证的状态" + subject.isAuthenticated());
        return result;
    }
}
