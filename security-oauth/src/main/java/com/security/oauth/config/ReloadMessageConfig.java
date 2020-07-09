package com.security.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 加载认证信息配置类
 * <p>当我们输入认证信息时，后台给我们的提示默认是英文的，但是我们想要更换成中文的。</p>
 * @author: stars
 * @date 2020年 07月 09日 10:01
 **/
@Configuration
public class ReloadMessageConfig {

    /***
     * 加载中文的认证提示信息
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new
                ReloadableResourceBundleMessageSource();
        //.properties 不要加到后面
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        return messageSource;
    }
}
