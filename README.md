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



# 二、基于RBAC动态认证账户

功能实现参考：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E5%85%AD%E5%9F%BA%E4%BA%8Erbac%E5%8A%A8%E6%80%81%E8%AE%A4%E8%AF%81%E8%B4%A6%E6%88%B7](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E5%85%AD%E5%9F%BA%E4%BA%8Erbac%E5%8A%A8%E6%80%81%E8%AE%A4%E8%AF%81%E8%B4%A6%E6%88%B7)

## 2.1、测试

## 2.1、测试

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090514365.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709172203734.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709172242517.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

## postman测试关键词

```
grant_type
password
username
password
```

