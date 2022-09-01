package com.lc.auth.center.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * @Author: Lu Cheng
 * @Data: 2022/4/30 23:43
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "luc-sys-properties")
@Data
public class LucSysProperties {
    public static final String PREFIX = "luc-sys-properties";
    public static final String ENABLE_REDIS_CACHE = "luc-sys-properties.enable-redis-cache";
    public static final String SESSION_PREFIX = "luc-sys-properties.session-prefix";
    private String enableRedisCache;

    private ShiroProperties shiroProperties;

    private JwtProperties jwtProperties;

    public String getEnableRedisCache() {
        return enableRedisCache;
    }

    public void setEnableRedisCache(String enableRedisCache) {
        this.enableRedisCache = enableRedisCache;
    }

    public ShiroProperties getShiroProperties() {
        return shiroProperties;
    }

    public void setShiroProperties(ShiroProperties shiroProperties) {
        this.shiroProperties = shiroProperties;
    }

    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }

    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
}
