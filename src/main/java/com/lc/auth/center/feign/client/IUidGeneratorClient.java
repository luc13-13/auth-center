package com.lc.auth.center.feign.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lucheng
 * @data: 2022/4/29 14:51
 * @version: 1.0
 */
@Configuration
public class IUidGeneratorClient {
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {

        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory factory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return factory;
    }

}
