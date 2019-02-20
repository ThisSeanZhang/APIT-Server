package io.whileaway.apit.shiro.config;

import io.whileaway.apit.shiro.entity.UrlPermission;
import io.whileaway.apit.shiro.service.UrlPermissionService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class ShiroConfiguration {

    @Autowired
    private UrlPermissionService urlPermissionService;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean()();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, String> validUrlPermission = urlPermissionService.findValidUrlPermission();
        validUrlPermission.put("/css/**", "anon");
//        validUrlPermission.put("/session", "anon");
        validUrlPermission.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(validUrlPermission);

//        shiroFilterFactoryBean.setLoginUrl("/session");
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;
    }

    @Bean
    public UserShiroRealm userShiroRealm(){
        UserShiroRealm myShiroRealm = new UserShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(userShiroRealm());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }
}
