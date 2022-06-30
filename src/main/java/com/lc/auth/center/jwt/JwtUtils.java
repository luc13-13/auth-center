package com.lc.auth.center.jwt;

import com.alibaba.fastjson.JSONObject;
import com.lc.auth.center.constant.JwtProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 工具类，用于创建{@link JwtToken}
 * @author: lucheng
 * @data: 2022/4/29 16:11
 * @version: 1.0
 */
@Data
@Component
@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
public class JwtUtils {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * TODO:token生成策略采用Shiro还是自定义？
     * 目前的token生成方式为 {@link Jwts#builder()}, 向其中注入密钥、过期时间等
     * @param loginName
     * @return
     */
    public String generateToken(String loginName) {
        log.info("JwtUtils=========当前系统Jwt配置: ", JSONObject.toJSONString(jwtProperties));
        long nowTime = System.currentTimeMillis();
        Date expireTime = new Date(nowTime + jwtProperties.getExpire() * 1000);

        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(loginName)
                .setIssuedAt(new Date(nowTime))
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS384,jwtProperties.getSecret())
                .compact();
    }

    /**
     * jwttoken中的  {@link Claims}
     * @param token
     * @return
     */
    public Claims getClaimByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
//        Map<String, Object> map = new HashMap<>();
//        map.put("token",token);
//        return new DefaultClaims(map);
        return claims;
    }

    /**
     * TODO:判断token是否过期，后面考虑用Redis判断
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        return this.getClaimByToken(token).getExpiration().before(new Date());
    }


}
