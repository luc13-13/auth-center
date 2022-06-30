package com.lc.auth.center.constant;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import lombok.Data;

/**
 * @author: lucheng
 * @data: 2022/4/30 23:43
 * @version: 1.0
 */
@SpringBootConfiguration(proxyBeanMethods = false)
@Configuration
@ConfigurationProperties(prefix = "luc-sys-properties")
@Primary
@Data
public class LucSysProperties {
    public static final String PREFIX = "luc-sys-properties";
    public static final String ENABLE_REDIS_CACHE = "luc-sys-properties.enable-redis-cache";
    public static final String SESSION_PREFIX = "luc-sys-properties.session-prefix";
    private String enableRedisCache;

    private ShiroProperties shiroProperties;

    private JwtProperties jwtProperties;

}
