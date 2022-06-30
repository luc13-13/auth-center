package com.lc.auth.center.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 开启 mybatis plus 的分页功能
 * @author: lucheng
 * @data: 2022/4/29 15:07
 * @version: 1.0
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * {@link MybatisPlusInterceptor}类似于WebMvcConfig中的拦截器，
     * sql注入时会根据 其中的 List<InnerInterceptor>对sql进行过滤
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }
}
