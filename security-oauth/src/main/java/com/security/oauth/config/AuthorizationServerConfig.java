package com.security.oauth.config;

import com.security.oauth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * 认证配置类
 * <p>{@code @EnableAuthorizationServer}开启SpringSecurity过滤连file，开启 OAuth2 认证服务器功能</p>
 * @author: stars
 * @create: 2020年 07月 08日 10:42
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 在 SpringSecurityBean 添加到容器了
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 引入AuthenticationManager 实例
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用户认证
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService ;

    /**
     * token管理方式，在TokenConfig类中已对添加到容器中了
     */
    @Autowired
    private TokenStore tokenStore ;

    /**
     * 注入数据源
     */
    @Autowired
    private DataSource dataSource;

    /***
     * 注入jwt转换器
     */
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 认证错误处理类
     *
     */
    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    /**
     * 授权码管理策略
     *
     * @return
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     *  创建jdbcClientDetailsService实例，并注入spring容器中，不要少了@Bean
     *  注意：访问修饰符不要写错了。
     * @return
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置被允许访问此认证服务器的客户端详情信息
     * 方式1：内存方式管理
     * 方式2：数据库管理
     * <p>authorizedGrantTypes授权类型, 可同时支持多种授权类型
     * <ul>
     *     <li>authorization_code：授权码</li>
     *     <li>password：密码模式</li>
     *     <li>implicit：简化模式</li>
     *     <li>client_credentials：客户端模式</li>
     *     <li>refresh_token：刷新令牌</li>
     * </ul></p>
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用内存方式
     /*   clients.inMemory()
                // 客户端id
                .withClient("ssoj-pc")
                // 客户端密码，要加密,不然一直要求登录, 获取不到令牌, 而且一定不能被泄露
                .secret(passwordEncoder.encode("ssoj-secret"))
                // 资源id, 如商品资源
                .resourceIds("product-server")
                // 授权类型, 可同时支持多种授权类型(authorization_code)表示授权码模式
                .authorizedGrantTypes("authorization_code","password","implicit","client_credentials","refresh_token")
                // 授权范围标识，哪部分资源可访问（all是标识，不是代表所有）
                .scopes("all")
                // false 跳转到授权页面手动点击授权，true 不用手动授权，直接响应授权码，
                .autoApprove(false)
                // 客户端回调地址
                .redirectUris("https://www.baidu.com/");*/
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 关于认证服务器端点配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // password 要这个 AuthenticationManager 实例
                .authenticationManager(authenticationManager)
                // 刷新令牌获取新的令牌是需要
                .userDetailsService(customUserDetailsService)
                // 令牌的管理方式，并指定JWT转换器 accessTokenConverter
                .tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter)
                // 授权码管理策略，针对授权码模式有效，会将授权码放到 auth_code 表，授权后就会删除它
                .authorizationCodeServices(jdbcAuthorizationCodeServices())
                // 认证错误处理类
                .exceptionTranslator(webResponseExceptionTranslator);
    }

    /**
     * 令牌端点的安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 所有人可访问 /oauth/token_key 后面要获取公钥, 默认拒绝访问
        security.tokenKeyAccess("permitAll()");
        // 认证后可访问 /oauth/check_token , 默认拒绝访问
        security.checkTokenAccess("isAuthenticated()");
    }
}
