package com.example.spring_test.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalWebMvcConfig implements WebMvcConfigurer {

    /**
     * 重写父类提供的跨域请求处理的接口
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                .allowedOriginPatterns("*")  // 允许所有来源
                .allowCredentials(true)      // 允许发送身份验证凭据
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD")
                .allowedHeaders("*")
                .exposedHeaders("Server", "Content-Length", "Authorization", "Access-Token",
                        "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
    }

    // addPathPatterns是拦截所有路径，excludePathPatterns是排除需要拦截的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/**");
                //.excludePathPatterns("/sso/idle/login");
    }
}


