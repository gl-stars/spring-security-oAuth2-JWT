# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述                                                       |
| ------ | ---------------------------------------------------------- |
| master | 最终需要采用的方案                                         |
| v1     | 完成授权码模式                                             |
| v2     | 密码模式登录                                               |
| v3     | 简化和客服端授权模式                                       |
| v4     | 实现中文提示信息，刷新token                                |
| v5     | 新增RBAC权限管理和redis管理令牌                            |
| v6     | 新增JDBC管理令牌和授权码保存到数据中                       |
| v7     | 将客户端数据保存到数据库中，配置令牌端点安全策略和检查令牌 |
| v8     | 新增RBAC动态认证账户                                       |
| v9     | jwt对称和非对称加密                                        |



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

