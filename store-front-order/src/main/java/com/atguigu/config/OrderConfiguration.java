package com.atguigu.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;

@Configuration
public class OrderConfiguration {
    public MessageConverter messageConverter() {
        return new MessageConverter();
    }
}
