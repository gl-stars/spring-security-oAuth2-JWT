package com.security.oauth.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码配置
 *
 * @version : 1.0.0
 * @author: GL
 * @create: 2020年 07月 11日 22:51
 **/
@Setter
@Getter
public class ValidateCodeProperties {
    /**
     * 设置认证通时不需要验证码的clientId
     */
    private String[] ignoreClientCode = {};
}
