package com.security.oauth.config;

import com.security.oauth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置类
 * <p>{@code @EnableWebSecurity}这个注解当中就包含了{@code @Configuration}注解了。</p>
 * @author: stars
 * @date 2020年 07月 08日 15:51
 **/
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 在 SpringSecurityBean 添加到容器了
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /***
     * 验证用户信息
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * 配置用户信息
     * <ul>
     *     <li>内存方式</li>
     *     <li>JDBC方式</li>
     * </ul>
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内存方式
       /* auth.inMemoryAuthentication().withUser("admin")
                // 一定要注意：这里的密码一定要加密！！！加密！！！加密！！！，获取授权码页面提示Bad credentials，有可能是用户名或者密码写错，另外就要考虑这里的密码是否加密了。
                .password(passwordEncoder.encode("123456"))
                // authorities("product") 指定权限为：product
                .authorities("product");*/
        // 数据库查询方式
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * 密码模式需要这个认证管理器
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
