package com.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器相关配置类
 * <p>@EnableResourceServer 标识为资源服务器，请求服务中的资源，就要带着token过来，找不到token或token是无效访问不了资源</p>
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 令牌管理策略，使用哪种类型认证。例如：内存方式还是redis，JDBC方式。
     * <p>例如使用redis保存认证信息，在配置文件的时候就需要将{@link TokenStore}这个实力注册到容器中。配置如下：
     * <pre>
     *     @Bean
     *      public TokenStore tokenStore() {
     *          Redis 管理令牌
     *          return new RedisTokenStore(redisConnectionFactory);
     *      }
     * </pre>
     * 使用redis存储认证信息，需要注入{@link org.springframework.data.redis.connection.RedisConnectionFactory}，如果保存信息是自己创建
     * </p>
     */
    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 当前资源服务器的资源id，认证服务会认证客户端有没有访问这个资源id的权限，有则可以访问当前服务
        resources.resourceId("product-server")
                .tokenStore(tokenStore)
        ;
    }
}
