package com.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Token管理工具
 * @author: stars
 * @date 2020年 07月 09日 13:47
 **/
@Configuration
public class TokenConfig {
    /**
     * Redis 管理令牌
     * 添加redis 依赖后, 容器就会有 RedisConnectionFactory 实例
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 注入JDBC数据源
     */
    @Autowired
    private DataSource dataSource ;

    /**
     * 指定令牌管理方式
     * <b>不要忘记@Bean添加到容器中</b>
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // redis 管理令牌
//        return new RedisTokenStore(redisConnectionFactory);
        // 注入数据源
        return new JdbcTokenStore(dataSource);
    }
}
