package com.security.oauth.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 客户端相关信息
 * <p>{@code oauth_client_details}这张数据表中{@code resource_ids}这个字段。<br />
 * 给个默认值，避免后面的工程没有配置。例如：客户端id，客户端密码等
 * </p>
 * @version : 1.0.0
 * @author: GL
 * @create: 2020年 07月 11日 23:04
 **/
@Getter
@Setter
public class ClientProperties {

    // 当前资源服务器的资源id，认证服务会认证客户端有没有访问这个资源id的权限，有则可以访问当前服务
    private String resourceId = "product-server";
}
