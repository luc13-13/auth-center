package com.lc.auth.center.config.cache;

import com.lc.auth.center.config.shiro.LucRedisSessionDAO;
import com.lc.auth.center.constant.LucSysProperties;
import com.lc.auth.center.constant.ShiroProperties;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.lc.auth.center.annotation.ConditionalOnRedisCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lucheng
 * @data: 2022/5/3 15:07
 * @version: 1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LucSysProperties.class)
@Data
@Slf4j
public class ICacheManager {
    private final LucSysProperties lucSysProperties;
    private final ShiroProperties shiroProperties;
    public ICacheManager(LucSysProperties lucSysProperties) {
        this.lucSysProperties = lucSysProperties;
        this.shiroProperties = lucSysProperties.getShiroProperties();
    }

    @Bean(name = "shiroRedisManager")
    @ConditionalOnRedisCache
    public RedisManager redisManager(RedisProperties redisProperties) {
        RedisManager redisManager = new RedisManager();
        redisManager.setDatabase(redisProperties.getDatabase());
        redisManager.setTimeout((int) shiroProperties.getSessionTimeout().getSeconds() * 1000);
        redisManager.setHost(redisManager.getHost());
        if(StringUtils.hasLength(redisProperties.getPassword())) {
            redisManager.setPassword(redisProperties.getPassword());
        }
        return redisManager;
    }

    @Bean(name = "shiroRedisCacheManager")
    @ConditionalOnRedisCache
    public RedisCacheManager redisCacheManager(@Qualifier("shiroRedisManager") RedisManager redisManager) {
        log.info("自动装配的shiroProperties: {}； 从LucSysProperties中获得的:{}",this.shiroProperties);
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        //
        redisCacheManager.setExpire((int) shiroProperties.getSessionTimeout().getSeconds());
        redisCacheManager.setKeyPrefix(shiroProperties.getSessionPrefix() + ":cache:");
        return redisCacheManager;
    }

    @Bean(name = "lucRedisSessionDAO")
    @ConditionalOnRedisCache
    public LucRedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        LucRedisSessionDAO lucRedisSessionDAO = new LucRedisSessionDAO(this.shiroProperties);
        lucRedisSessionDAO.setRedisManager(redisManager);
        return lucRedisSessionDAO.initialize();
    }


    @Bean(name = "ehCacheManager")
    @ConditionalOnMissingBean(RedisCacheManager.class)
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return ehCacheManager;
    }

    @Bean(name = "memorySessionDAO")
    @ConditionalOnMissingBean(RedisSessionDAO.class)
    public MemorySessionDAO memorySessionDAO(@Qualifier("javaUuidSessionIdGenerator") SessionIdGenerator javaUuidSessionIdGenerator) {
        MemorySessionDAO memorySessionDAO = new MemorySessionDAO();
        memorySessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator);
        return memorySessionDAO;
    }

    @Bean(name = "javaUuidSessionIdGenerator")
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }




}
