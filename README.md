# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述                                                         |
| ------ | ------------------------------------------------------------ |
| master | 最终需要采用的方案                                           |
| v1     | 完成授权码模式                                               |
| v2     | 密码模式登录                                                 |
| v3     | 简化和客服端授权模式                                         |
| v4     | 实现中文提示信息，刷新token                                  |
| v5     | 新增RBAC权限管理和redis管理令牌                              |
| v6     | 新增JDBC管理令牌和授权码保存到数据中                         |
| v7     | 将客户端数据保存到数据库中，配置令牌端点安全策略和检查令牌   |
| v8     | 新增RBAC动态认证账户                                         |
| v9     | jwt对称、非对称加密、配置资源服务器，使用token直接访问资源服务器 |



# 二、jwt对称加密

功能实现：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/6%E3%80%81JWT%E7%AE%A1%E7%90%86%E4%BB%A4%E7%89%8C.md#%E4%B8%89%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E5%AE%9E%E7%8E%B0jwt%E5%AF%B9%E7%A7%B0%E5%8A%A0%E5%AF%86](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/6%E3%80%81JWT%E7%AE%A1%E7%90%86%E4%BB%A4%E7%89%8C.md#%E4%B8%89%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E5%AE%9E%E7%8E%B0jwt%E5%AF%B9%E7%A7%B0%E5%8A%A0%E5%AF%86)

## 测试：

请求地址

```http
localhost:8090/auth/oauth/token
```

- 获取token

随便使用一个获取令牌的模式，看一下令牌是否变成jwt中token的样式，并做token检查。我就使用密码模式。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710091257598.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710090957258.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710091146216.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020071009121835.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

关键词

```
grant_type
password
username
password
```

- 校验token

请求地址：

```http
http://localhost:8090/auth/oauth/check_token
```

关键词：

```
token
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710091538172.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710091650604.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



# 三、配置资源服务器

主要就是在资源服务器里面指定资源id就可以了，但是强烈建议不要写在认证服务器里面去，因为这个是不同的资源可能有不同的资源id，认证的方式不同。例如：我写了一个签到系统，我想创在一个机器人来充当用户打卡，但是真人打卡需要密码模式或者授权码模式登陆。但是机器人这个服务器我想使用客户端模式登陆即可，所以就要对这两个服务器的客服端id设置为不同的。

创建`ResourceServerConfig`类继承`ResourceServerConfigurerAdapter`，在打上 `@Configuration`和`@EnableResourceServer`注解，@EnableResourceServer 标识为资源服务器，请求服务中的资源，就要带着token过来，找不到token或token是无效访问不了资源。实现 `configure(ResourceServerSecurityConfigurer resources)`方法，配置资源id。

![在这里插入图片描述](https://img-blog.csdnimg.cn/202007101147512.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710115556631.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

在创建一个前端控制器，写一个方法测试，看一下带token是否能获取到资源。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710114927309.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



## 测试

获取token就不用说了，上面jwt对称加密才说过。

获取资源，访问路径

```http
http://localhost:8090/auth/list
```

> 千万不要忘记 auth 服务器名称，因为我给服务器名称是起了一个名字的。![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710115209270.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710115335970.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)









# 常见的问题

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710114003317.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

.资源服务器没有这定客服端id

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020071011545545.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710115556631.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



# 四、非对称加密

功能实现

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710154718646.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

这样就可以了，测试和上面一样。