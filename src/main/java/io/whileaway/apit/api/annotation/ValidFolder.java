package io.whileaway.apit.api.annotation;

import io.whileaway.apit.base.PermissionType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ValidFolder {
    PermissionType value();
}
