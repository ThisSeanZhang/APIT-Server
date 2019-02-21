package io.whileaway.apit.shiro.service;

import io.whileaway.apit.shiro.repository.UrlPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class UrlPermissionServiceImpl implements UrlPermissionService {

    private final UrlPermissionRepository urlPermissionRepository;

    @Autowired
    public UrlPermissionServiceImpl(UrlPermissionRepository urlPermissionRepository) {
        this.urlPermissionRepository = urlPermissionRepository;
    }

    @Override
    public LinkedHashMap<String, String> findValidUrlPermission() {
        return urlPermissionRepository.findAll()
                .stream().collect(
                        LinkedHashMap::new,
                        (map, urlPermission) -> map.put(urlPermission.getUrl(),urlPermission.getPermission()),
                        LinkedHashMap::putAll
                );
    }
}
