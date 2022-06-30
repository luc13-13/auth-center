package com.lc.auth.center.handler;

import com.luc.framework.core.mvc.Status;
import com.luc.framework.core.mvc.WebResult;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lucheng
 * @data: 2022/4/29 20:40
 * @version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 shiro 抛出的异常，以及HTTP的401异常，表示用户未认证
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public WebResult handleShiro(ShiroException e) {
        return WebResult.response(Status.generate(401,e.getMessage()),null);
    }

    


}
