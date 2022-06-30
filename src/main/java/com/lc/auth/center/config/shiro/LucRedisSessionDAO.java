package com.lc.auth.center.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.lc.auth.center.config.cache.LucRedisObjectSerializer;
import com.lc.auth.center.config.cache.LucRedisStringSerializer;
import com.lc.auth.center.constant.LucSysProperties;
import com.lc.auth.center.constant.ShiroProperties;
import com.lc.auth.center.utils.RedisUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.serializer.ObjectSerializer;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 继承{@link AbstractSessionDAO 利用redisTemplate，实现将session缓存至redis}
 * @author: lucheng
 * @data: 2022/5/3 15:49
 * @version: 1.0
 */
@Slf4j
public class LucRedisSessionDAO extends RedisSessionDAO {

    private ShiroProperties shiroProperties;

    public LucRedisSessionDAO(ShiroProperties shiroProperties){
        this.shiroProperties = shiroProperties;
        this.setKeyPrefix(shiroProperties.getSessionPrefix());
        this.setSessionInMemoryTimeout(shiroProperties.getSessionTimeout().getSeconds());
    }

    /**
     * shiro内已经封装了redisManager进行缓存的增删改查操作，这里我们只需要修改
     * shiro中和redis有关的参数即可，并在调用该sessionDAO使进行初始化
     */
    public LucRedisSessionDAO initialize() {
        this.setKeySerializer(new LucRedisStringSerializer());
        this.setValueSerializer(new ObjectSerializer());
        return this;
    }


//    /**
//     * 创建Session，注入sessionId, 并将session保存至redis
//     * @param session
//     * @return
//     */
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = sessionIdGenerator.generateId(session);
//        log.info("com.lc.auth.center.config.shiro.LucSessionDAO 创建sessionID：sessionId ->{}",sessionId);
//        //调用父类方法绑定sessionID
//        assignSessionId(session,sessionId);
////        redisTemplate
////                .boundValueOps(LucSysProperties.SESSION_PREFIX + sessionId)
////                .set(session);
//        redisUtils
//                .set( shiroProperties.getSessionPrefix() + sessionId,
//                session,
//                shiroProperties.getSessionTimeout().toMillis());
//        return sessionId;
//    }
//
//    /**
//     * 从sessionID获取session
//     * @param serializable
//     * @return
//     */
//    @Override
//    protected Session doReadSession(Serializable serializable) {
//        log.info("com.lc.auth.center.config.shiro.LucSessionDAO#doReadSession: 根据sessionID->{} 读取session",shiroProperties.getSessionPrefix() + serializable);
//        Session session = (Session) redisUtils.getObject(shiroProperties.getSessionPrefix() + serializable);
//        return session;
//    }
//
//
//    @Override
//    public void update(Session session) throws UnknownSessionException {
//        try {
//            Serializable sessionId = session.getId();
//            redisUtils.update(sessionId.toString(), session, shiroProperties.getSessionTimeout().toMillis());
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UnknownSessionException("sessionID错误");
//        }
//    }
//
//    @Override
//    public void delete(Session session) {
//        redisUtils.expired(true, session.getId().toString());
//    }
//
//    @Override
//    public Collection<Session> getActiveSessions(){
//        return redisUtils.getByPrefix(shiroProperties.getSessionPrefix())
//                .stream().map(s -> (Session)s).collect(Collectors.toSet());
//    }
}
