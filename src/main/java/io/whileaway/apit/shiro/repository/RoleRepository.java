package io.whileaway.apit.shiro.repository;

import io.whileaway.apit.shiro.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
