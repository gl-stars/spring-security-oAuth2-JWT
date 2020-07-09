# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述                        |
| ------ | --------------------------- |
| master | 最终需要采用的方案          |
| v1     | 完成授权码模式              |
| v2     | 密码模式登录                |
| v3     | 简化和客服端授权模式        |
| v4     | 实现中文提示信息，刷新token |

# 二、实现中文提示信息（上面文档中没有）

1. 观察 spring-security-core-xxx.jar 下有国际化配置文件 messages_xxx.properties
2. 默认 ReloadableResourceBundleMessageSource 是加载了 messages.properties 英文配置文件；
3. 应该手动指定加载 messages_zh_CN.properties 中文配置文件。
4. 在 mengxuegu-security-core 创建 com.mengxuegu.security.confifig.ReloadMessageConfifig

## 2.1、实现

创建 `ReloadMessageConfig`配置类，实例化`ReloadableResourceBundleMessageSource`类，并指定加载中文提示信息。

```java
@Bean
public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new
            ReloadableResourceBundleMessageSource();
    //.properties 不要加到后面
    messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
    return messageSource;
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709101334128.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

## 2.2、测试

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709101452525.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

# 三、刷新token

> 刷新令牌只能是授权码和密码模式下有效

添加刷新令牌的授权类型，`refresh_token`。

操作：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E4%B8%80%E5%88%B7%E6%96%B0%E4%BB%A4%E7%89%8C](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E4%B8%80%E5%88%B7%E6%96%B0%E4%BB%A4%E7%89%8C)

## 测试

### 获取刷新令牌

使用授权码模式或者密码模式都可以获取到刷新令牌，我这里使用密码模式。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090514365.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070909064782.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090734155.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



- postman测试关键词

```
grant_type
password
username
password
```

### 根据刷新令牌获取新的令牌

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709104334464.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070910451028.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



- 关键词

```
refresh_token
grant_type
```

