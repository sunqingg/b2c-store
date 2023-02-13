package com.atguigu.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.atguigu.config.CacheConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration extends CacheConfiguration {
    public MessageConverter messageConverter() {
        return new MessageConverter();
    }
}
