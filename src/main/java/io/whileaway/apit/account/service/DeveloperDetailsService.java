package io.whileaway.apit.account.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.entity.Role;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeveloperDetailsService implements UserDetailsService {

    private final DeveloperService developerService;
    private final RoleService roleService;

    @Autowired
    public DeveloperDetailsService(DeveloperService developerService, RoleService roleService) {
        this.developerService = developerService;
        this.roleService = roleService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Developer developer = developerService.findByName(username);
        if (developer == null) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
//            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(Role role: roleService.getDeveloperAllRole(developer.getDeveloperId()))
        {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
            System.out.println(role.getRole());
        }
        return new org.springframework.security.core.userdetails.User(developer.getDeveloperName(),
                developer.getDeveloperPass(), authorities);
    }
}
