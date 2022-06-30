package com.lc.auth.center.web.controller;

import com.lc.auth.center.model.UserDO;
import com.lc.auth.center.service.UserService;
import com.luc.framework.core.mvc.WebResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

/**
 * @author: lucheng
 * @data: 2022/5/1 20:51
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUserList")
    @RequiresPermissions("浏览全部内容")
    public WebResult<List<UserDO>> getUserList(){
        List<UserDO> userDOList = userService.list();
//        List<UserVO> voList = new ArrayList<>();
        return WebResult.successData(userDOList);
    }
}
