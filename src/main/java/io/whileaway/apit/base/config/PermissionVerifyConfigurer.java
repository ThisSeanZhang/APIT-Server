package io.whileaway.apit.base.config;

import io.whileaway.apit.api.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class PermissionVerifyConfigurer implements WebMvcConfigurer {


    private final ProjectInterceptor projectInterceptor;

    @Autowired
    public PermissionVerifyConfigurer(ProjectInterceptor projectInterceptor) {
        this.projectInterceptor = projectInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/projects/**");
//        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }
}
