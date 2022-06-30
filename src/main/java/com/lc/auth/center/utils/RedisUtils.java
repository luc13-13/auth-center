package com.lc.auth.center.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author: lucheng
 * @data: 2021/12/12 15:52
 * @version: 1.0
 */
@Component
@RequiredArgsConstructor
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${spring.application.name}")
    private String keyPrefix;

    private List<String> generateKeyList(List<String> keys) {
        List<String> keyList = keys.stream().map(k->generateKey(k)).collect(Collectors.toList());
        return keyList;
    }
    private String generateKey(String key) {
        return keyPrefix + "-" + key;
    }

    /**
     * 判断是否存在key
     * @param key
     * @return true存在  false不存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(generateKey(key));
    }

    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    public String get(String key) {
        key = generateKey(key);
        return redisTemplate.opsForValue().get(key).toString();
    }

    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    public Object getObject(String key){
        key = generateKey(key);
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据多个key获取value列表
     * @param keys
     * @return
     */
    public List<Object> getByKeys(List<String> keys) {
        List<String> keyList = generateKeyList(keys);
        return redisTemplate.opsForValue().multiGet(keyList);
    }

    public List<Object> getByPrefix(String keyPrefix){
        keyPrefix = generateKey(keyPrefix);
        Set<String> keys = redisTemplate.keys(keyPrefix);
        if (Collections.isEmpty(keys)) {
            return null;
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 向内存中写入键值对，时间单位为millisecond
     * @param key 根据业务动态设置key
     * @param object 业务对象
     * @param timeout 缓存失效时间
     * @return
     */
    public boolean set(String key, Object object, long timeout) {
        key = generateKey(key);
        try {
            //不设置时间默认不失效
            //redisTemplate.opsForValue().set(key,object);
            // 这里long为偏移量,单位为字节，覆盖后面的字符串
            //redisTemplate.opsForValue().set(key,object,1000);
            //redisTemplate.opsForValue().setIfAbsent();
            //redisTemplate.opsForValue().setIfPresent();
            //设置有效时间及单位
//            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(object), timeout, TimeUnit.MILLISECONDS);
            redisTemplate.opsForValue().set(key, object, timeout, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将map中的键值对放入缓存
     * @param map
     */
    public void multiSet(Map<String, Object> map) {
        List<String> keys = map.keySet().stream().collect(Collectors.toList());
        System.out.println(keys);
        Map<String, Object> mapRes = new HashMap<>();
        keys.forEach(k->{
            mapRes.put(generateKey(k),map.get(k));
        });
        System.out.println(mapRes);
        redisTemplate.opsForValue().multiSet(mapRes);
    }

    public Long increment(String key, Integer incr) {
        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
        if(!hasKey(key)) {
            set(key, 0, 50000);
            return 0L;
        }
        key = generateKey(key);
        return redisTemplate.opsForValue().increment(key, incr);
    }

    /**
     * 使key过期
     * @param key
     */
    public void expired(boolean hasPrefix, String ... key){
        if(key != null && key.length > 0) {
            if(key.length == 1) {
                this.redisTemplate.delete(hasPrefix ? "" + key : keyPrefix + key);
            }
            else {
                redisTemplate.delete(hasPrefix ? Arrays.asList(key) :  generateKeyList(Arrays.asList(key)));
            }
        }
    }

    /**
     * 更新key的value和有效时间
     * @param key
     * @param obj
     * @param timeOut
     */
    public void update(String key, Object obj, long timeOut) throws Exception{
        if(! hasKey(key)) {
            throw new Exception("redis key不存在！");
        }
        redisTemplate.opsForValue().set(key,obj,timeOut);
    }
}
