package com.security.oauth.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Redis 管理令牌
 * @version : 1.0.0
 * @author: GL
 * @create: 2020年 07月 11日 16:55
 **/
@ConditionalOnProperty(prefix = "ssoj.oauth2.token.store", name = "type", havingValue = "redis")
public class AuthRedisTokenStore {

    /**
     * Redis 管理令牌
     * 添加redis 依赖后, 容器就会有 RedisConnectionFactory 实例
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 指定令牌管理方式
     * <b>不要忘记@Bean添加到容器中</b>
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // redis 管理令牌
        return new RedisTokenStore(redisConnectionFactory);
    }
}
