package io.whileaway.apit.account.controller;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.CreateSession;
import io.whileaway.apit.account.service.SessionService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.SessionKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;
    private final HttpSession session;

    @Autowired
    public SessionController(SessionService sessionService, HttpSession session) {
        this.sessionService = sessionService;
        this.session = session;
    }

    @PostMapping()
    public Result<Developer> createSession(@Valid @RequestBody CreateSession createSession, BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        Result<Developer> result = sessionService.createSession(createSession);
        session.setAttribute(SessionKeyConstant.CURRENT_DEVELOPER, result.getData());
        return result;
    }

    @DeleteMapping()
    public Result deleteSession() {
        session.removeAttribute(SessionKeyConstant.CURRENT_DEVELOPER);
        session.invalidate();
        return ResultUtil.success();
    }
}
