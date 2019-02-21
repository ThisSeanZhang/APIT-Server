package io.whileaway.apit.shiro.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnAutoInjectFilterConfig {

    @Bean
    public FilterRegistrationBean shiroLoginFilterRegistration(ShiroLoginFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }


//    @Bean
//    public FilterRegistrationBean shiroRolesFilterRegistration(ShiroRolesFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
//    @Bean
//    public FilterRegistrationBean shiroPermsFilterRegistration(ShiroPermsFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
//
//    @Bean
//    public FilterRegistrationBean kickoutSessionFilterRegistration(KickoutSessionFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }

}
