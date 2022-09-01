package com.lc.auth.center.jwt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

/**
 * shiro中的{@link AuthenticatingFilter} 继承自javax.servlet.Filter
 * @author: lucheng
 * @data: 2022/4/29 15:51
 * @version: 1.0
 */
@Component
@Slf4j
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 对servlet请求
     * @param servletRequest
     * @param servletResponse
     * @return 如果请求头中有 Authentication, 则生成token
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwtToken = request.getHeader("Authentication");
        boolean isRememberMe = Boolean.parseBoolean(request.getHeader("rememberMe"));
        log.info("com.lc.blog.shiro.jwt 校验ServletRequest中jwtToken{},rememberMe:{}",jwtToken);
        return StringUtils.hasLength(jwtToken) ? new JwtToken(jwtToken,isRememberMe) : null;
    }

    /**
     * 判断请求是否被拒绝，这里加入放行策略: token存在且没有过期
     * @param servletRequest
     * @param servletResponse
     * @return true-访问被拒绝，false-访问通过
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwtToken = request.getHeader("Authentication");
        if (!StringUtils.hasLength(jwtToken)){
            return true;
        } else {
            //校验token是否过期
            Claims claims = jwtUtils.getClaimByToken(jwtToken);
            if(claims == null || jwtUtils.isTokenExpired(jwtToken)){
                throw new ExpiredCredentialsException("登陆已失效，请重新登陆");
            }
        }
        //token认证通过，允许访问
        return executeLogin(servletRequest,servletResponse);
    }

    /**
     * 登陆失败向则将异常 e {@link AuthenticationException} 写入response,
     * 如果 e.getCause()为空，则写入未知异常
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            httpServletResponse.getWriter().print (Optional.ofNullable(e.getCause()).orElse(new AuthenticationException("未知错误，登陆失败")).getMessage());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return false;
    }

    /**
     * 重写父类 {@link org.apache.shiro.web.filter.PathMatchingFilter}
     * 在处理ServletRequest之前，解决跨域问题，将请求头中写入
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);

    }
}
