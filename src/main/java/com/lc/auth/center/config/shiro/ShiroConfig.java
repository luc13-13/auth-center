package com.lc.auth.center.config.shiro;

import com.lc.auth.center.config.ShiroLifeStyleBeanPostProcessorConfig;
import com.lc.auth.center.config.cache.ICacheManager;
import com.lc.auth.center.config.cache.SystemCacheSelector;
import com.lc.auth.center.constant.LucSysProperties;
import com.lc.auth.center.jwt.JwtUtils;
import com.lc.auth.center.listener.ShiroSessionListener;
import com.lc.auth.center.config.realm.AccountRealm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;

import lombok.RequiredArgsConstructor;

/**
 * shiroConfig主要功能为
 * 1、创建realm
 * 2、创建安全管理器securityManager
 * 3、配置shiro过滤器工厂shiroFilterFactoryBean
 * 4、开启对shiro注解的支持AuthorizationAttributeSourceAdvisor
 * @author: lucheng
 * @data: 2022/5/1 14:56
 * @version: 1.0
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter({ShiroLifeStyleBeanPostProcessorConfig.class,ICacheManager.class, SystemCacheSelector.class})
@RequiredArgsConstructor
@Slf4j
public class ShiroConfig {
    private final LucSysProperties lucSysProperties;

    /** 1、realm
     * 修改shiroConfig生命周期，解决配置类无法读取到的问题
     * @return
     */

    private AccountRealm accountRealmProcessor(AccountRealm accountRealm) {
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        accountRealm.setAuthenticationCachingEnabled(true);
        accountRealm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        accountRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        accountRealm.setAuthorizationCacheName("authorizationCache");
        // 开启缓存
        accountRealm.setCachingEnabled(true);
        return accountRealm;
    }


    /** 2、 securityManager
     * shiro安全管理，向其中注入realm/缓存管理/会话管理/rememberMe管理
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("sessionManager") SessionManager sessionManager,
                                                     @Nullable @Qualifier("ehCacheManager") EhCacheManager ehCacheManager,
                                                     @Nullable @Qualifier("shiroRedisCacheManager")  RedisCacheManager redisCacheManager,
                                                     @Qualifier("cookieRememberMeManager")CookieRememberMeManager rememberMeManager,
                                                     AccountRealm accountRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        accountRealmProcessor(accountRealm);
        securityManager.setRealm(accountRealm);
        securityManager.setCacheManager(redisCacheManager != null ? redisCacheManager : ehCacheManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;

    }

    /**
     * DefaultWebSecurityManager -> 2.1 缓存管理, 已放入{@link ICacheManager}
     * @return
     */
//    @Bean(name = "ehCacheManager")
//    @ConditionalOnMissingBean(RedisCacheManager.class)
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
//        return ehCacheManager;
//    }

    /**
     * DefaultWebSecurityManager -> 2.2 会话管理 其中包括监听设置/缓存设置/缓存访问对象/会话cookie
     * @return
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(@Nullable @Qualifier("ehCacheManager") EhCacheManager ehCacheManager,
                                         @Nullable @Qualifier("memorySessionDAO") MemorySessionDAO memorySessionDAO,
                                         @Nullable @Qualifier("shiroRedisCacheManager") RedisCacheManager redisCacheManager,
                                         @Nullable @Qualifier("lucRedisSessionDAO") RedisSessionDAO redisSessionDAO,
                                         @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 采用ehCacheManager
        sessionManager.setCacheManager(redisCacheManager != null ? redisCacheManager : ehCacheManager);
        // 设置会话监听
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());

        // 设置缓存访问对象. sessionDAO中需要包括 cacheManager/缓存名字/cacheId
        sessionManager.setSessionDAO(redisSessionDAO != null ? redisSessionDAO : memorySessionDAO);
        sessionManager.setSessionIdCookie(sessionIdCookie);

        // session过期时间, shiro的单位是毫秒，这里进行转换
        sessionManager.setGlobalSessionTimeout(lucSysProperties.getShiroProperties().getSessionTimeout().toMillis());
        log.info(" sessionManager======== sessionIdCookie: {}",sessionIdCookie);
        return sessionManager;
    }
    /**
     * 2.2 SessionManager会话管理 -> 2.2.1 会话访问对象，相当于对会话进行CRUD的DAO
     * 已在 {@link com.lc.auth.center.config.cache.ICacheManager}中进行管理
     * @param javaUuidSessionIdGenerator
     * @return
     */
//    @Bean(name = "memorySessionDAO")
//    @ConditionalOnMissingBean(RedisSessionDAO.class)
//    public MemorySessionDAO memorySessionDAO(@Qualifier("javaUuidSessionIdGenerator") SessionIdGenerator javaUuidSessionIdGenerator) {
//        MemorySessionDAO memorySessionDAO = new MemorySessionDAO();
//        memorySessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator);
//        return memorySessionDAO;
//    }

    /**
     * 2.2.1 会话访问对象SessionDAO -> (1) SessionDAO进行CRUD时采用的UID生成器
     * @return
     */


    /**
     * 2.2 SessionManager会话管理 -> 2.2.2 与会话UID关联的cookie
     * @return
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        //-1 标识浏览器关闭时sid失效
        sessionIdCookie.setMaxAge(-1);
        sessionIdCookie.setPath("/");
        sessionIdCookie.setHttpOnly(true);
        return sessionIdCookie;
    }

    /**
     * DefaultWebSecurityManager -> 2.3 rememberMe管理
     * @param simpleRememberCookie
     * @return
     */
    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager(@Qualifier("simpleRememberCookie") SimpleCookie simpleRememberCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleRememberCookie);
        // 设置cookie密钥
        //注意： rememberMeManager只能接收长度为16的byte[],要保证字符串的数组长度为16
        // TODO:为什么一个英文单词占一个字节，但得到的数组长度和字符串长度不匹配？
        cookieRememberMeManager.setCipherKey(Base64.decode(
                Base64.encode("LUCHENG_TIANWEIF".getBytes(StandardCharsets.UTF_8))
        ));
        return cookieRememberMeManager;
    }




    /**
     * 2.3.1 rememberMe管理 -> rememberMeCookie
     * @return
     */
    @Bean(name = "simpleRememberCookie")
    public SimpleCookie simpleRememberCookie() {
        SimpleCookie simpleRememberCookie = new SimpleCookie("rememberMe");
        // cookie有效时间，单位秒
        simpleRememberCookie.setMaxAge(3600);
        //
        simpleRememberCookie.setHttpOnly(true);
        simpleRememberCookie.setPath("/");
        return simpleRememberCookie;
    }

    /**
     * @param securityManager 使用静态注入将securityManager放入容器
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(@Qualifier("defaultWebSecurityManager") SecurityManager securityManager) {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager});
        return factoryBean;
    }

    /**
     * 3、配置shiro过滤工厂，为不同资源设置不同访问权限
     * 注意：初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Lazy @Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager,
                                                         @Qualifier("shiroFilterChainDefinition") ShiroFilterChainDefinition filterChainDefinition) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登陆URL
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登陆成功后跳转URL
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权跳转界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // 设置不同路径的访问权限以及免认证的url， key为接口路径，value为shiro中的权限
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition.getFilterChainMap());
        return shiroFilterFactoryBean;
    }

    /**
     * 4、开启shiro认证授权注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Lazy DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean(name = "shiroFilterChainDefinition")
    @ConditionalOnMissingBean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition filterChainDefinition = new DefaultShiroFilterChainDefinition();
        Map<String, String> filterMap = new LinkedHashMap<>();
        /**
             * anon 没有参数，表示可以匿名使用。
             * authc 表示需要认证(登录)才能使用，没有参数
             * authcBasic 没有参数表示httpBasic认证
             * logout 表示添加shiro的默认退出
             * noSessionCreation
             * perms 参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
             * port port[8091],当请求的url的端口不是8091是跳转到schemal://serverName:8091?url,其中schmal是协议http或https等，serverName是你访问的host,8091是url配置里port的端口，url是你访问的url里的？后面的参数。也就是默认更改端口
             * rest 根据请求的方法，相当于/user/**=perms[user:method] ,其中method为post，get，delete等。
             * roles 参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
             * ssl 没有参数，表示安全的url请求，协议为https
             * user 没有参数表示必须存在用户，当登入操作时不做检查
         * ?：匹配一个字符，如/admin?, 将匹配/admin1，但不匹配/admin 或/admin/；
         * *：匹配零个或多个字符串，如/admin/*, 将匹配/admin、/admin123，但不匹配/admin/1；
         * **：匹配路径中的零个或多个路径，如/admin/**, 将匹配/admin/a 或/admin/a/b
             */
        filterMap.put("/logout","logout");
        //配置不登录可以访问的资源，anon 表示资源都可以匿名访问
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/u/**", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/doc.html", "anon");
        filterMap.put("/simpleBillRepertory/getShareStockUrl", "anon");
        filterMap.put("/alicheck", "anon");
        filterMap.put("/api-docs/**", "anon");
        filterMap.put("/article/manage/**","perms[user:manager]");
        filterMap.put("/article/visit/**","perms[user:visit]");
        filterMap.put("/user/**","perms[浏览全部内容]");
        filterChainDefinition.addPathDefinitions(filterMap);
        return filterChainDefinition;
    }
}
