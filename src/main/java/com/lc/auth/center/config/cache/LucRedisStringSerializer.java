package com.lc.auth.center.config.cache;

import java.nio.charset.Charset;
import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author: lucheng
 * @data: 2022/5/3 20:07
 * @version: 1.0
 */
public class LucRedisStringSerializer implements RedisSerializer<String> {

    private final Charset charset;
    public static final StringRedisSerializer US_ASCII;
    public static final StringRedisSerializer ISO_8859_1;
    public static final StringRedisSerializer UTF_8;

    public LucRedisStringSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public LucRedisStringSerializer(Charset charSet) {
        Assert.notNull(charSet,"Charset must not be null!");
        this.charset = charSet;
    }

    static {
        US_ASCII = new StringRedisSerializer(StandardCharsets.US_ASCII);
        ISO_8859_1 = new StringRedisSerializer(StandardCharsets.ISO_8859_1);
        UTF_8 = new StringRedisSerializer(StandardCharsets.UTF_8);
    }




    @Override
    public byte[] serialize(String s) throws SerializationException {
        return s == null ? null : s.getBytes(this.charset);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return bytes == null ? null : new String(bytes, this.charset);
    }
}
