package com.lc.auth.center.web.request;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * @author: lucheng
 * @data: 2022/4/30 14:10
 * @version: 1.0
 */
@Data
@RequiredArgsConstructor
public class LoginRequest {

    private String loginName;

    private String password;

    private String token;

    private String rememberMe;

}
