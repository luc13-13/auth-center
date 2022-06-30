package com.lc.auth.center.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;

import lombok.Data;

/**
 * @author: lucheng
 * @data: 2022/4/30 22:18
 * @version: 1.0
 */
@Data
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = LucSysProperties.PREFIX + "shiro-properties")
public class ShiroProperties {
    /** 会话过期时间  单位秒    */
    private Duration sessionTimeout;
    private String sessionPrefix;
    /** cookie过期时间  单位秒*/
    private Duration cookieTimeout;
    // Jwts生成token时的密钥，同时也用于和token生成Claims
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String unauthorizedUrl;

    @Override
    public String toString() {
        return "ShiroProperties{" +
                "sessionTimeout=" + sessionTimeout +
                ", cookieTimeout=" + cookieTimeout +
                ", anonUrl='" + anonUrl + '\'' +
                ", loginUrl='" + loginUrl + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", logoutUrl='" + logoutUrl + '\'' +
                ", unauthorizedUrl='" + unauthorizedUrl + '\'' +
                '}';
    }
    //    private String secret;
//    // token过期时间
//    private long expire;
//    // token头信息
//    private String header;
}
