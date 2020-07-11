package com.security.oauth.config;

import com.security.oauth.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器相关配置类
 * <p>资源服务器相关配置应该放在需要使用该jar的地方，但是我为了整洁，应用该jar的地方不需要
 * 关心权限认证这一块的所有东西。如果这个功能留给后面去写，那么{@linkplain ResourceServerConfigurerAdapter}
 * 这个类后面的工程是引用不到，就意味着这个功能后面不应用Oauth2相应的jar包是不可能做的。所以我就在这里实现了。
 * 后期可以利用配置的方式来进行相关配置，实现相同的功能。</p>
 * <p>@EnableResourceServer 标识为资源服务器，请求服务中的资源，就要带着token过来，找不到token或token是无效访问不了资源</p>
 * @author: stars
 * @date 2020年 07月 10日 15:59
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 引入客户端信息
     */
    @Autowired
    private SecurityProperties properties ;

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
        System.out.println(properties.getClient().getResourceId());
        // 当前资源服务器的资源id，认证服务会认证客户端有没有访问这个资源id的权限，有则可以访问当前服务
        resources.resourceId(properties.getClient().getResourceId())
                .tokenStore(tokenStore)
        ;
    }
}
