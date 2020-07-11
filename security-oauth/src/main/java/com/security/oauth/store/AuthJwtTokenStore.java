package com.security.oauth.store;

import com.security.oauth.converter.CustomUserAuthenticationConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.io.IOException;

/**
 * 认证服务器使用 JWT RSA 非对称加密令牌
 * <p>{@code @ConditionalOnProperty}注解使用讲解。<pre>
 *     prefix: 配置的前缀
 *     name: 属性名
 *     havingValue: 属性值
 *     matchIfMissing：如果不指定，是否为默认。默认是false，不指定，true指定。所以，如果没有配置，默认加载该类。
 * </pre></p>
 * @version : 1.0.0
 * @author: GL
 * @create: 2020年 07月 11日 16:25
 **/
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "ssoj.security.auth", name = "type", havingValue = "authJwt",matchIfMissing = true)
public class AuthJwtTokenStore {

    /**
     * 指定令牌管理方式
     * @param jwtAccessTokenConverter
     * @return
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * 令牌转换器
     * <p>配置公钥和私钥，本来公钥应该是要放到资源服务器里面的，但是我不打算制作微服务的，所以就好的系统都是直接集成这个工程，而不是同伙网络连接
     * 所以我将公钥和私钥的详细都放在一起了。</p>
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
            log.info("Caused by: 获取公钥失败"+ AuthJwtTokenStore.class);
            e.printStackTrace();
        }
        converter.setVerifierKey(publicKey);
        DefaultAccessTokenConverter tokenConverter = (DefaultAccessTokenConverter)converter.getAccessTokenConverter();
        // 想根扩展UserAuthenticationConverter接口，添加用户id字段，但是并没有搞定。
        tokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());
        return converter;
    }
}
