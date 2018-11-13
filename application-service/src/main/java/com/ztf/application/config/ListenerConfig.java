package com.ztf.application.config;

import com.ztf.application.listener.TestListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {
    @Bean
    public ServletListenerRegistrationBean testListener () {
        ServletListenerRegistrationBean<TestListener> bean = new ServletListenerRegistrationBean<>();
        bean.setListener(new TestListener());
        return bean;
    }
}
