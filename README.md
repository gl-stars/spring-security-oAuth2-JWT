# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述                 |
| ------ | -------------------- |
| master | 最终需要采用的方案   |
| v1     | 完成授权码模式       |
| v2     | 密码模式登录         |
| v3     | 简化和客服端授权模式 |

​	

# 二、简化模式

添加客服端授权类型即可 `implicit`。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709092337558.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

## 2.1、测试

- 启动

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070909241788.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

在浏览器上输入访问地址

```http
http://localhost:8090/auth/oauth/authorize?client_id=ssoj-pc&response_type=token
```

> `auth`：服务器名称，在yml中自己配置的
>
> `client_id`：客服端id
>
> `response_type`：相应类型是`token`

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709092800185.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070909291045.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

授权成功后，会跳转到回调地址。回调地址后面带上授权相关信息。同样这里授权也可以设置为自动授权的。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709093033704.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

```
https://www.baidu.com/
#access_token=d0c9bfed-9ef6-4ed8-b422-652d85c63bc9
&token_type=bearer
&expires_in=43199
&scope=all
```

> access_token：令牌
>
> token_type：令牌类型，返回都是这个值
>
> expires_in：令牌的时差，一般是12个小时
>
> scope：访问范围，在 .scopes("all")这里配置的。

# 三、客服端模式

在认证服务器里面添加一个授权类型即可。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709093718327.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

## 测试

客服端模式不存在用户名和密码的，具体参考：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md#42%E5%AE%A2%E6%88%B7%E7%AB%AF%E6%A8%A1%E5%BC%8F](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md#42%E5%AE%A2%E6%88%B7%E7%AB%AF%E6%A8%A1%E5%BC%8F)

- 启动

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070909241788.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

- 访问地址：

```http
http://localhost:8090/auth/oauth/token
```

> auth：工程名称

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709094121339.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709094218263.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



# 相关参数

```
implicit
client_credentials
grant_type
```







# postman测试注意不要少了授权类型

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709093525341.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)