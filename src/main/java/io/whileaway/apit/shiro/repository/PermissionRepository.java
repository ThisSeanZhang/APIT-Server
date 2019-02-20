package io.whileaway.apit.shiro.repository;

import io.whileaway.apit.shiro.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
