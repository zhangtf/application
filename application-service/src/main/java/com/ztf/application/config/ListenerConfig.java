package com.ztf.application.config;

import com.ztf.application.listener.TestListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监听器配置
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
@Configuration
public class ListenerConfig {
    /**
     * 测试监听器
     */
    @Bean
    public ServletListenerRegistrationBean testListener () {
        ServletListenerRegistrationBean<TestListener> bean = new ServletListenerRegistrationBean<>(new TestListener());
        return bean;
    }

//    @Bean
//    public ServletListenerRegistrationBean applicationStartedListener () {
//        ServletListenerRegistrationBean<ApplicationStartedListener> bean = new ServletListenerRegistrationBean<>(new ApplicationStartedListener());
//        return bean;
//    }
}
