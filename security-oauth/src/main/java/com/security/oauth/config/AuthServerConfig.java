package com.security.oauth.config;

import org.springframework.context.annotation.ComponentScan;

/**
 * 扫描将指定类的实例注入spring中
 * <p>注意启动类并不在本工程当中，所以该工程额很多类是不能被spring扫描到，所以需要手动指定。
 * {@code @SpringBootApplication}注解只能所买当前包及其子包，所以该工程的@Configuration,@Service ,@Component等等
 * 这些注解就扫描不到了。</p>
 * @author: stars
 * @date 2020年 07月 08日 17:17
 **/
@ComponentScan("com.security.oauth")
public class AuthServerConfig {
}
