package com.lc.auth.center.config.cache;

import com.lc.auth.center.constant.LucSysProperties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import java.util.Arrays;
import java.util.Properties;

/**
 * 如果未开启Redis缓存，则排除Redis相关Bean
 *
 * @author MrBird
 */
//@Configuration(proxyBeanMethods = false)
//@AutoConfigureAfter(LucSysProperties.class)
public class SystemCacheSelector {//implements BeanDefinitionRegistryPostProcessor {

    private volatile boolean finished = false;
    private boolean enableRedisCache;

    //@Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        if (!finished) {
            // 时机较早，必须手动获取配置
            YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
            yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yml"));
            Properties properties = yamlPropertiesFactoryBean.getObject();
            if (properties != null) {
                enableRedisCache = Boolean.parseBoolean(properties.getProperty(LucSysProperties.ENABLE_REDIS_CACHE));
            }
            if (!enableRedisCache) {
                String[] beanDefinitionNames = registry.getBeanDefinitionNames();
                Arrays.stream(beanDefinitionNames).filter(beanDefinitionName ->
                        StringUtils.containsIgnoreCase(beanDefinitionName, "redis")
                                || StringUtils.containsIgnoreCase(beanDefinitionName, "jedis")
                                || StringUtils.containsIgnoreCase(beanDefinitionName, "lettuce"))
                        .forEach(registry::removeBeanDefinition);
                finished = true;
            }
        }
    }

   // @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}
