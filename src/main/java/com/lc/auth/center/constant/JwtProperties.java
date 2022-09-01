package com.lc.auth.center.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.Data;

/**
 * @author: lucheng
 * @data: 2022/4/30 23:48
 * @version: 1.0
 */
@Data
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = LucSysProperties.PREFIX + ".jwt-properties")
public class JwtProperties {

    /** 密钥， 用于对token进行加密*/
    private String secret;
    /** 过期时间*/
    private long expire;
    /** httpResponse中对header*/
    private String header;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
