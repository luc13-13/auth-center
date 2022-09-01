package com.lc.auth.center.jwt;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * 由于没有使用UserNamePasswordToken，
 * @author: lucheng
 * @data: 2022/4/29 16:10
 * @version: 1.0
 */
public class JwtToken implements AuthenticationToken, RememberMeAuthenticationToken {
    private String token;
    /** true: 记住我 or false: 不记住 */
    private boolean rememberMe;

    @Override
    public boolean isRememberMe() {
        if (this.rememberMe) {

        }
        return this.rememberMe;
    }

    public JwtToken(String token, boolean rememberMe) {
        this.rememberMe = rememberMe;
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String toString() {
        return "JwtToken{token:"+token+",isRememberMe:"+ rememberMe +"}";
    }
}
