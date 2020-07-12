package com.security.oauth.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 数据库管理临牌
 * <p>{@{@code @ConditionalOnProperty}使用{@linkplain AuthJwtTokenStore}</p>
 * @version : 1.0.0
 * @author: GL
 * @create: 2020年 07月 11日 16:20
 **/
@Configuration
@ConditionalOnProperty(prefix = "ssoj.security.auth", name = "type", havingValue = "db")
public class AuthDbTokenStore {

    /**
     * 注入数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 指定令牌管理方式
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }
}
