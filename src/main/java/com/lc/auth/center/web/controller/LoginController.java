package com.lc.auth.center.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.auth.center.service.RoleService;
import com.lc.auth.center.web.request.LoginRequest;
import com.lc.auth.center.jwt.JwtToken;
import com.lc.auth.center.jwt.JwtUtils;
import com.lc.auth.center.model.UserDO;
import com.lc.auth.center.service.UserService;
import com.luc.framework.core.mvc.Status;
import com.luc.framework.core.mvc.WebResult;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lucheng
 * @data: 2022/4/30 14:04
 * @version: 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("")
@Slf4j
public class LoginController extends BaseLoginController{

    private final UserService userService;
    private final JwtUtils jwtUtils;


    @PostMapping("/login")
    public WebResult<String> login(@Validated @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        log.info("接收到登陆请求LoginRequest: {}",loginRequest);
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",loginRequest.getLoginName())
                .eq("invalid", 1);
        UserDO userDO = userService.getOne(wrapper);
        log.info("查询到的用户: {}", JSONObject.toJSONString(userDO));
        // TODO: 密码解密
        if(! StringUtils.pathEquals(loginRequest.getPassword(), userDO.getPwd())) {
            return WebResult.response(Status.generate(401,""),"密码错误");
        }
        String jwt = jwtUtils.generateToken(userDO.getLoginName());
        log.info("创建jwtToke: {}", jwt);
        response.addHeader("Authorization",jwt);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        JwtToken jwtToken = new JwtToken(jwt,Boolean.parseBoolean(loginRequest.getRememberMe()));
        log.info("是否选择记住我:{}",Boolean.parseBoolean(loginRequest.getRememberMe()));
        Subject subject = SecurityUtils.getSubject();
        subject.login(jwtToken);
        // TODO:检查
        log.info("检查记住我:{}",SecurityUtils.getSubject().isRemembered());
        return WebResult.successData(JSONObject.toJSONString(userDO));
    }

    @GetMapping("/success")
    public WebResult<String> success() {

        return WebResult.successData("测试！");
    }

}
