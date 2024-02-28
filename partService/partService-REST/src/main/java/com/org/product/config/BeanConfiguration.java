package com.org.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.product.generic.processor.CommonObjectMapper;

@Configuration
public class BeanConfiguration {

    @Bean
    public ObjectMapper getObjectMapper() {
        return CommonObjectMapper.createObjectMapper();
    }
}
