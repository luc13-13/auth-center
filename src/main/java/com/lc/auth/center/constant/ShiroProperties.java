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
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = LucSysProperties.PREFIX + ".shiro-properties")
public class ShiroProperties {
    /** 会话过期时间  单位秒    */
    private Duration sessionTimeout;
    private String sessionPrefix;
    private String cookiePrefix;

    /** cookie过期时间  单位秒*/
    private Duration cookieTimeout;

    /** cookie过期时间  单位秒*/
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String unauthorizedUrl;

    public Duration getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Duration sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getSessionPrefix() {
        return sessionPrefix;
    }

    public void setSessionPrefix(String sessionPrefix) {
        this.sessionPrefix = sessionPrefix;
    }

    public Duration getCookieTimeout() {
        return cookieTimeout;
    }

    public void setCookieTimeout(Duration cookieTimeout) {
        this.cookieTimeout = cookieTimeout;
    }

    public String getAnonUrl() {
        return anonUrl;
    }

    public void setAnonUrl(String anonUrl) {
        this.anonUrl = anonUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }
}
