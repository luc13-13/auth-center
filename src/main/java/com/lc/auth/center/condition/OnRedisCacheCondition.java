package com.lc.auth.center.condition;

import com.lc.auth.center.constant.LucSysProperties;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import lombok.extern.slf4j.Slf4j;

/**
 * 重写 {@link Condition#matches(ConditionContext, AnnotatedTypeMetadata)}
 * 从Spring已加载的context中寻找 {@link LucSysProperties#ENABLE_REDIS_CACHE}
 * 存在则说明开启了redis缓存，返回true
 * @author: lucheng
 * @data: 2022/5/1 0:16
 * @version: 1.0
 */
@Slf4j
public class OnRedisCacheCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        log.info("全局配置: {}", LucSysProperties.ENABLE_REDIS_CACHE);
        return Boolean.parseBoolean(
                context.getEnvironment()
                        .getProperty(LucSysProperties.ENABLE_REDIS_CACHE));
    }
}
