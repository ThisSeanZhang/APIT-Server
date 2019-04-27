package io.whileaway.apit.base.config;

import io.whileaway.apit.api.interceptor.ProjectInterceptor;
import io.whileaway.apit.base.interceptor.JsonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class PermissionVerifyConfigurer implements WebMvcConfigurer {

    private final ProjectInterceptor projectInterceptor;
    private final JsonInterceptor jsonInterceptor;

    @Autowired
    public PermissionVerifyConfigurer(ProjectInterceptor projectInterceptor, JsonInterceptor jsonInterceptor) {
        this.projectInterceptor = projectInterceptor;
        this.jsonInterceptor = jsonInterceptor;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/").setViewName("/index");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/projects/**");
        registry.addInterceptor(jsonInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        registry.jsp("/",".html");
    }
}
