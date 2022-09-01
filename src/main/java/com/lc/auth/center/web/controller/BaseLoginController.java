package com.lc.auth.center.web.controller;

import com.lc.auth.center.jwt.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: lucheng
 * @data: 2022/4/30 13:58
 * @version: 1.0
 */
@Slf4j
public class BaseLoginController {

    protected String getAuthentication() {
        log.info("============ BaseLoginController： 校验token ============");
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String accessToken = request.getHeader("Authentication");
        AccountProfile currentAccount = (AccountProfile) SecurityUtils.getSubject().getPrincipal();

        return accessToken;
    }

}
