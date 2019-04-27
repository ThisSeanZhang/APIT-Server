package io.whileaway.apit.account.annotation;

import io.whileaway.apit.base.PermissionType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ValidDeveloper {
    PermissionType value();
}