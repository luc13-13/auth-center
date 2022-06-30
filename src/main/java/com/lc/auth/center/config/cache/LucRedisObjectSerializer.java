package com.lc.auth.center.config.cache;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.apache.commons.lang3.ArrayUtils;
import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author: lucheng
 * @data: 2022/5/3 20:16
 * @version: 1.0
 */
public class LucRedisObjectSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET;
    private final JavaType javaType;
    private ObjectMapper objectMapper = new ObjectMapper();

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }

    public LucRedisObjectSerializer(Class<T> type) {
        this.javaType = this.getJavaType(type);
    }

    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "'objectMapper' must not be null");
        this.objectMapper = objectMapper;
    }



    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return new byte[0];
        } else {
            try {
                return this.objectMapper.writeValueAsBytes(o);
            } catch (Exception var3) {
                throw new org.springframework.data.redis.serializer.SerializationException("Could not write JSON: " + var3.getMessage(), var3);
            }
        }

    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (ArrayUtils.isEmpty(bytes)) {
            return null;
        } else {
            try {
                return this.objectMapper.readValue(bytes, 0, bytes.length, this.javaType);
            } catch (Exception var3) {
                throw new org.springframework.data.redis.serializer.SerializationException("Could not read JSON: " + var3.getMessage(), var3);
            }
        }
    }
}
