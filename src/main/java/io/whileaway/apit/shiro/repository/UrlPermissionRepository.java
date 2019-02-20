package io.whileaway.apit.shiro.repository;

import io.whileaway.apit.shiro.entity.UrlPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlPermissionRepository extends JpaRepository<UrlPermission, Long> {
}
