package com.lc.auth.center;

import com.lc.auth.center.constant.JwtProperties;
import com.lc.auth.center.constant.LucSysProperties;
import com.lc.auth.center.constant.ShiroProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: lucheng
 * @data: 2021/11/27 17:17
 * @version: 1.0
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"com.lc.auth.center.mapper"})
@EnableConfigurationProperties({LucSysProperties.class, JwtProperties.class, ShiroProperties.class, RedisProperties.class})
@EnableDiscoveryClient
@EnableFeignClients
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
