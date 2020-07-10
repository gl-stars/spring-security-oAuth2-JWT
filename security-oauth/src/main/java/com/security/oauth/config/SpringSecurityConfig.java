package com.security.oauth.config;

import com.security.handler.OauthLogoutSuccessHandler;
import com.security.oauth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * 安全配置类
 * <p>{@code @EnableWebSecurity}这个注解当中就包含了{@code @Configuration}注解了。<br />
 * </p>
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
     * 全局用户
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 退出登陆操作
     */
    @Autowired
    private LogoutHandler oauthLogoutHandler;

    @Autowired(required = false)
    private AuthenticationEntryPoint authenticationEntryPoint;

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
     * 资源权限配置
     * <p>
     * <blockquote><pre>
     *         1、被拦截的资源
     *         2、资源对应的角色权限
     *         3、定制认证方式httpBasic()、httpForm
     *         4、定制登陆页面、登陆请求地址、错误处理方式
     *         5、自定义SpringSecurity过滤器
     *     </pre></blockquote>
     * </p>
     * <p>
     * {@link HttpSecurity#addFilterAt(Filter, Class)} (Filter, Class)}我们调用了 HttpSecurity#addFilterAt(A, B.class) 方法时，A拦截器要先于B拦截器执行。
     * </p>
     *
     * @param http
     * @throws Exception
     * @see org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 认证服务器相关资源全部放行，用于处理认证
                .authorizeRequests()
                .anyRequest()
                //授权服务器关闭basic认证
                .permitAll()
                .and()
                // 注销处理
                .logout()
                // 注销地址，默认为：/logout。点进去就能看到
//                .logoutUrl("/oauth/remove/token")
                // 自定义退出时处理程序
                .logoutSuccessHandler(new OauthLogoutSuccessHandler())
                // 退出清楚缓存
                .addLogoutHandler(oauthLogoutHandler)
                // 指定是否在注销用户是清楚，默认为false
                .clearAuthentication(true)
                .and()
                // 关闭跨站请求伪造
                .csrf().disable()
                // 解决不允许显示在iframe的问题
//                .headers().frameOptions().disable().cacheControl();
        ;

        // 基于密码 等模式可以无session,不支持授权码模式
        if (authenticationEntryPoint != null) {
            // 无权限登录时，解决异常
            http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
            // 在spring security配置中禁用session。请求接口后，浏览器的cookie中还是有JSESSIONID，spring boot 内置的Tomcat中还是创建了session，
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } else {
            // 授权码模式单独处理，需要session的支持，此模式可以支持所有oauth2的认证
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        }
    }

    /**
     * 密码模式需要这个认证管理器
     * <p>这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户</p>
     * @return 认证管理对象
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
