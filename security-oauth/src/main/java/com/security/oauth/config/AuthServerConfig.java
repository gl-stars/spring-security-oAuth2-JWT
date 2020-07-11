package com.security.oauth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 扫描将指定类的实例注入spring中，手动实例化类到spring容器中。
 * <p>注意启动类并不在本工程当中，所以该工程额很多类是不能被spring扫描到，所以需要手动指定。{@code @ComponentScan({"com.security.oauth","com.security.user"})}
 * {@code @SpringBootApplication}注解只能扫描当前包及其子包，所以该工程的@Configuration,@Service ,@Component等等
 * 这些注解就扫描不到了。</p>
 * <p>在每个mapper接口上都书写@Mapper注解，我觉得比较麻烦，所以同意在这里指定mapper接口扫描。@MapperScan("")
 * 只扫描一个接口可以这样写，如果扫描多个接口需要在里面添加一个大括号，用逗号隔开。@MapperScan({"","",""})和@ComponentScan
 * 注解的写法一样。</p>
 * @author: stars
 * @date 2020年 07月 08日 17:17
 **/
@ComponentScan({"com.security.oauth","com.security.user"})
@MapperScan("com.security.user.mapper")
public class AuthServerConfig {

    /***
     * 加载中文的认证提示信息
     * <p>当我们输入认证信息时，后台给我们的提示默认是英文的，但是我们想要更换成中文的。</p>
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

    /**
     * 加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
