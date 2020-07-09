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



# 二、将客户端数据保存到数据库中

功能实现参考：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E5%9B%9B%E5%B0%86%E5%AE%A2%E6%9C%8D%E7%AB%AF%E4%BF%A1%E6%81%AF%E4%BF%9D%E5%AD%98%E5%88%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E4%B8%AD](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E5%9B%9B%E5%B0%86%E5%AE%A2%E6%9C%8D%E7%AB%AF%E4%BF%A1%E6%81%AF%E4%BF%9D%E5%AD%98%E5%88%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E4%B8%AD)

## 2.1、测试

这几种认证模式当中，不管使用哪一种模式测试都行，但是为了方便，我就使用用户和密码认证。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090514365.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

访问地址：

```http
http://localhost:8090/auth/oauth/token
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709161001374.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090734155.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

- postman测试关键词

```
grant_type
password
username
password
```

## 2.2、常见的错误

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709161304779.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

注意：遇到页面什么都没有显示的情况下，后台也没有打印任何报错信息。这种情况可能是客服端id或者客服端密码或者是访问路径，请求方式出现了问题，仔细检查。

<font style="font-weight: bold;font-size:20px;font-family:微软雅黑" color=blue>这个问题一定要切记，因为我之前就遇到这个问题。后台任何错误信息都没有，postman也没有返回任何信息，这是一件非常难以排查的问题。</font>



# 三、配置令牌端点安全策略和检查令牌

功能实现参考：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E4%BA%94%E9%85%8D%E7%BD%AE%E4%BB%A4%E7%89%8C%E7%AB%AF%E7%82%B9%E5%AE%89%E5%85%A8%E7%AD%96%E7%95%A5%E5%92%8C%E6%A3%80%E6%9F%A5%E4%BB%A4%E7%89%8C](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E4%BA%94%E9%85%8D%E7%BD%AE%E4%BB%A4%E7%89%8C%E7%AB%AF%E7%82%B9%E5%AE%89%E5%85%A8%E7%AD%96%E7%95%A5%E5%92%8C%E6%A3%80%E6%9F%A5%E4%BB%A4%E7%89%8C)

## 3.1、测试

获取到令牌，上面就有密码模式获取，不在描述。

## 3.2、检查令牌

访问地址：

```http
http://localhost:8090/auth/oauth/check_token
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709165005793.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709165046560.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)





