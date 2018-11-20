package com.ztf.application.config;


import com.ztf.application.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 过滤器配置
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
@Configuration
public class FilterConfig {
    /**
     * 字符过滤器
     */
    @Bean
    public FilterRegistrationBean characterEncodingFilter () {
        FilterRegistrationBean<CharacterEncodingFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CharacterEncodingFilter("UTF-8", Boolean.TRUE));
        bean.setOrder(1);
        bean.setName("characterEncodingFilter");
        bean.addUrlPatterns("/*");
        return bean;
    }

    /**
     * 测试过滤器
     * order 从5开始，前面默认有四个
     */
    @Bean
    public FilterRegistrationBean testFilter () {
        FilterRegistrationBean<TestFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TestFilter());
        bean.setOrder(5);
        bean.addUrlPatterns("/test/*");
        return bean;
    }

}
