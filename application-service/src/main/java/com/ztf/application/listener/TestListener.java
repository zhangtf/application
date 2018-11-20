package com.ztf.application.listener;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 测试监听器
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
public class TestListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("TestListener sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("TestListener sessionDestroyed");
    }
}
