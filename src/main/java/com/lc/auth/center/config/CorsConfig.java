package com.lc.auth.center.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 重写 WebMvcConfigurer中的
 * @author: lucheng
 * @data: 2022/4/30 13:51
 * @version: 1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //主要，这里只能用allowedOriginPatterns，之前的allowedOrigin已被废弃
        registry.addMapping("/**")
                .allowedOriginPatterns("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
