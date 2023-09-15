package com.todook.crocodile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.todook.crocodile.domain.auth.token.UserTokenService;
import com.todook.crocodile.interceptor.RefererCheckInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final CrocodileConfig crocodileConfig;
    private final UserTokenService userTokenService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefererCheckInterceptor(crocodileConfig, userTokenService))
                // all
                .addPathPatterns("/**")
                // swagger
                .excludePathPatterns("/v3/api-docs")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/webjars/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }
}

