package com.lc.auth.center.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;
import java.util.UUID;

/**
 * 修改 mybatis plu 的 uid 生成方式，当DO类的id字段被
 * 注解@TableId(value = "id", type = IdType.ASSIGN_ID)
 * 修饰，则在使用 Dao.insert()方法时，不需要指定id,
 * 自动根据自定义的nextId()获取
 * @author: lucheng
 * @data: 2022/4/29 14:40
 * @version: 1.0
 */
@Component
public class IUidGenerator implements IdentifierGenerator {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 如果请求超时，返回JVM生成的uid
     * @param entity
     * @return
     */
    @Override
    public Number nextId(Object entity) {
        Long uid = Optional.ofNullable(
                restTemplate.getForObject("192.168.223.128:8888/uid/nextId",Long.class)
        ).orElse(UUID.randomUUID().getLeastSignificantBits());
        return uid;
    }
}
