package com.lc.auth.center.feign.server;

import com.lc.auth.center.jwt.JwtUtils;
import com.luc.framework.core.mvc.Status;
import com.luc.framework.core.mvc.WebResult;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Lu Cheng
 * @Date : 2022/9/4 23:54
 * @Version : 1.0
 */
@Api(tags = "feign")
@RestController
@RequestMapping("/feign")
@AllArgsConstructor
public class RequestHandler {

    JwtUtils jwtUtils;

    @GetMapping("/checkPerm")
    public WebResult<String> checkPermission(@RequestParam String token,
                                             @RequestParam String url) {
        jwtUtils.getClaimByToken(token);
        SecurityUtils.getSubject().checkPermission(token);
        return WebResult.response(Status.generate(200, "校验通过"), "true");
    }
}
