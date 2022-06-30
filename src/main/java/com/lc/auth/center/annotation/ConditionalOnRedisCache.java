package com.lc.auth.center.annotation;

import com.lc.auth.center.condition.OnRedisCacheCondition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解可以标注于METHOD和TYPE
 * 当 {@link Condition#matches(ConditionContext, AnnotatedTypeMetadata)}返回 true 时，
 * Conditional注解标注的内容生效。
 * 因此，该注解的作用条件为OnRedisCachedCondition
 * @author: lucheng
 * @data: 2022/5/1 0:15
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnRedisCacheCondition.class)
public @interface ConditionalOnRedisCache {
}
