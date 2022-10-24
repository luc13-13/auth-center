package com.lc.auth.center.filter;

import com.lc.auth.center.jwt.JwtToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重写shiro中对请求的过滤方式，在这里实现token过期时间的刷新
 * @Author : Lu Cheng
 * @Date : 2022/10/24 21:18
 * @Version : 1.0
 */
public class JwtAuthenticationFilter extends BasicHttpAuthenticationFilter {

    /**
     * shiro对请求的过滤方式， 返回为true为放行， 返回为false为拒绝。
     * 在这里校验token并尝试进行刷新， 刷新成功则为通过， 否则将请求跳转到登陆界面
     * @param request 进入servlet的请求
     * @param response
     * @param mappedValue
     * @return true 允许登陆 false不允许登陆
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 如果request.token登陆失败会抛出AuthenticationException，将其catch并刷新token
        try{
            executeLogin(request, response);
        } catch (Exception e) {
            // 捕获到异常，尝试刷新token
            if(refreshToken(request, response)){
                return true;
            }
            // TODO 刷新失败则将response重定向到登陆界面
        }

        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return super.isLoginAttempt(request, response);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //尝试用request.token进行登陆
        JwtToken jwtToken = new JwtToken("request.getToken",true);
        getSubject(request, response).login(jwtToken);
        //如果登陆失败会抛出AuthenticationException
        return true;
    }

    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        return true;
    }

    private void redirectLogin(ServletResponse response, String message) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            httpServletResponse.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
