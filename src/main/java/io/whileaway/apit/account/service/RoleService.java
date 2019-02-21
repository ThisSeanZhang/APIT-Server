package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getDeveloperAllRole(Long developerId);
}
