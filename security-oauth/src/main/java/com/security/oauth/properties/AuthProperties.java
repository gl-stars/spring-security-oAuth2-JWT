package com.security.oauth.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 认证配置
 *
 * @version : 1.0.0
 * @author: GL
 * @create: 2020年 07月 11日 22:51
 **/
@Setter
@Getter
public class AuthProperties {
    /**
     * 配置要认证的url（默认不需要配置）
     *
     * 优先级大于忽略认证配置`zlt.security.ignore.httpUrls`
     * 意思是如果同一个url同时配置了`忽略认证`和`需要认证`，则该url还是会被认证
     */
    private String[] httpUrls = {};

    /**
     * url权限配置
     */
    private UrlPermissionProperties urlPermission = new UrlPermissionProperties();

    /***
     * token管理方式
     * <ul>
     *     <li>db：数据库管理方式</li>
     *     <li>authJwt：JWT方式</li>
     *     <li>redis：redis方式</li>
     * </ul>
     */
    private String type ;
}
