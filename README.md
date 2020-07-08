# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述               |
| ------ | ------------------ |
| master | 最终需要采用的方案 |
| v1     | 完成授权码模式     |

# 二、依赖

其实在工程中引入的依赖有点多，其实现在v1分支用到的只有两个。

```xml
<!--spring mvc相关的-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- Spring Security、OAuth2 和JWT等 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
```

`web`依赖主要是使用到内置`tomcat`中的`file`这个过滤器。



# 三、测试

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200708173506436.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

## 3.1、获取授权码

在浏览器输入获取授权码这个地址：

获取授权码地址：[http://localhost:8090/auth/oauth/authorize?client_id=ssoj-pc&response_type=code](http://localhost:8090/auth/oauth/authorize?client_id=ssoj-pc&response_type=code)

```http
http://localhost:8090/auth/oauth/authorize?client_id=ssoj-pc&response_type=code
```

> auth ：当前工程的名称，在application.yml文件中配置的。
>
> /oauth/authorize ：访问授权码的端点
>
> client_id=客服端id
>
> response_type=code： 固定写法

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200708173619649.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

访问获取授权码这个地址后，或回到登录页面，输入用户名和密码登录。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200708173937619.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

登录成功后，如果设置自动授权，那么就会回到回调地址，将授权码放在回到地址后面。如果是手动授权，会弹出一个授权页面，需要手动授权才会有授权码。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200708174400395.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070817443821.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

## 3.2、获取token

请求地址：`post` 请求

```http
http://localhost:8090/auth/oauth/token
```

> auth：工程名称

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200708174821116.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200708174948783.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



#### 相关参数

```
http://localhost:8090/auth/oauth/token
```

`grant_type`

`authorization_code`

`code`

