package io.whileaway.apit.base.config;

import io.whileaway.apit.account.service.DeveloperDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 本想按照此步骤做，后来想想可以自己先进行实现简单的，地址
     * https://blog.csdn.net/u012373815/article/details/54632176
     * https://blog.csdn.net/u012373815/article/details/54633046
     * https://blog.csdn.net/u012373815/article/details/55225079
     */

    private final DeveloperDetailsService developerDetailsService;

    @Autowired
    public WebSecurityConfig(DeveloperDetailsService developerDetailsService) {
        this.developerDetailsService = developerDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(developerDetailsService); //user Details Service验证

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll(); //注销行为任意访问


    }
}
