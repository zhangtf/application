package com.ztf.application.config;

import com.ztf.application.interceptor.TestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {
    /**
     * 测试拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/test/**").excludePathPatterns("/test/insert");
    }
}
