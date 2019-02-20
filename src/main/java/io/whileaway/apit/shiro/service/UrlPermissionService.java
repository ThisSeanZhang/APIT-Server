package io.whileaway.apit.shiro.service;

import java.util.LinkedHashMap;

public interface UrlPermissionService {

    LinkedHashMap<String,String> findValidUrlPermission();

}
