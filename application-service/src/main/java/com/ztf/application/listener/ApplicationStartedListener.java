package com.ztf.application.listener;


import com.ztf.application.rsa.RSAUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听器
 * 监听应用启动
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    /**
     * 公钥文件路径
     */
    @Value("${public.key.path}")
    private String PUBLIC_KEY_PATH;
    /**
     * 私钥文件路径
     */
    @Value("${private.key.path}")
    private String PRIVATE_KEY_PATH;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        RSAUtils.init(PUBLIC_KEY_PATH, PRIVATE_KEY_PATH);
    }
}
