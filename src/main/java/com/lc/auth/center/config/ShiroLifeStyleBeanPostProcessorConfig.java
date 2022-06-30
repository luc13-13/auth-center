package com.lc.auth.center.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lucheng
 * @data: 2022/5/2 1:05
 * @version: 1.0
 */
@Configuration
public class ShiroLifeStyleBeanPostProcessorConfig {
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
}
