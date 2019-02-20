package io.whileaway.apit.api.annotation;

import io.whileaway.apit.api.PermissionType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CheckProjectPermission {
    PermissionType value();
}
