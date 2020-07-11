package com.security.oauth.properties;

import com.security.oauth.config.ResourceServerConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 配置类
 * <p>将所有有关权限认证方面的配置，全部归纳在这里。</p>
 * @version : 1.0.0
 * @author: GL
 * @create: 2020年 07月 11日 22:51
 **/
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "ssoj.security")
public class SecurityProperties {

    /**
     * 认证相关配置
     */
    private AuthProperties auth = new AuthProperties();

    /**
     * 验证码相关
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 客户端先关信息
     */
    private ClientProperties client = new ClientProperties() ;

}
