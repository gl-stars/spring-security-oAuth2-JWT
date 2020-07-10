package com.security.oauth.config;

import com.security.oauth.converter.CustomUserAuthenticationConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Token管理工具
 * @author: stars
 * @date 2020年 07月 09日 13:47
 **/
@Slf4j
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
   /* @Bean
    public TokenStore tokenStore() {
        // redis 管理令牌
//        return new RedisTokenStore(redisConnectionFactory);
        // 注入数据源
//        return new JdbcTokenStore(dataSource);

        // 使用jwt管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }*/

    /**
     * 指定令牌管理方式
     * <b>不要忘记@Bean添加到容器中</b>
     * @return
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * 配置公钥和私钥，本来公钥应该是要放到资源服务器里面的，但是我不打算制作微服务的，所以就好的系统都是直接集成这个工程，而不是同伙网络连接
     * 所以我将公钥和私钥的详细都放在一起了。
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对称加密进行签名令牌，资源服务器也要采用此密钥来进行解密,来校验令牌合法性
//        converter.setSigningKey("abcdefg");

        // 使用私钥加密，第1个参数就是密钥证书文件，第2个参数 密钥口令(-keypass), 私钥进行签名
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        // 指定非对称加密 oauth2 别名
        converter.setKeyPair(factory.getKeyPair("oauth2"));
        // 使用公钥解密
        ClassPathResource resource = new ClassPathResource("public.txt");
        String publicKey = null;
        try {
            publicKey = IOUtils.toString(resource.getInputStream(), "UTF-8");
        } catch (IOException e) {
            log.info("Caused by: 获取公钥失败"+TokenConfig.class);
            e.printStackTrace();
        }
        converter.setVerifierKey(publicKey);
        DefaultAccessTokenConverter tokenConverter = (DefaultAccessTokenConverter)converter.getAccessTokenConverter();
        // 想根扩展UserAuthenticationConverter接口，添加用户id字段，但是并没有搞定。
        tokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());
        return converter;
    }
}
