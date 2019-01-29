package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.request.CreateSession;
import io.whileaway.apit.base.Result;

public interface SessionService {

    Result<Developer> createSession(CreateSession createSession);
}
