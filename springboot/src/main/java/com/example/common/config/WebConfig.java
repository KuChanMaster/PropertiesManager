package com.example.common.config;

import com.example.entity.Account;
import com.example.utils.TokenUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements  WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Resource
    private TokenUtils tokenUtils;

    // 加自定义拦截器JwtInterceptor，设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        Account account =TokenUtils.getCurrentUser();


        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/files/**");
    }
}