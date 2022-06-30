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
    private boolean isRememberMe;

    @Override
    public boolean isRememberMe() {
        return this.isRememberMe;
    }

    public JwtToken(String token, boolean isRememberMe) {
        this.isRememberMe = isRememberMe;
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
        return "JwtToken{token:"+token+",isRememberMe:"+isRememberMe+"}";
    }
}
