package com.lc.auth.center.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author: lucheng
 * @data: 2022/4/30 23:48
 * @version: 1.0
 */
@Data
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = LucSysProperties.PREFIX+".jwt-properties")
public class JwtProperties {
    private String secret;
    private long expire;
    private String header;
}
