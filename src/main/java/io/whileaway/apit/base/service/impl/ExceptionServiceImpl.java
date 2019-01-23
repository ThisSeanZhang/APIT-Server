package io.whileaway.apit.base.service.impl;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ErrorResponse;
import io.whileaway.apit.base.service.ExceptionService;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Override
    public void throwTheException() {
        throw new CommonException(ErrorResponse.ERROR);
    }
}
