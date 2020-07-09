# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述                            |
| ------ | ------------------------------- |
| master | 最终需要采用的方案              |
| v1     | 完成授权码模式                  |
| v2     | 密码模式登录                    |
| v3     | 简化和客服端授权模式            |
| v4     | 实现中文提示信息，刷新token     |
| v5     | 新增RBAC权限管理和redis管理令牌 |

# 二、RBAC权限管理

实现步骤：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/4%E3%80%81RBAC%E6%9D%83%E9%99%90%E7%AE%A1%E7%90%86.md#rbac%E6%9D%83%E9%99%90%E7%AE%A1%E7%90%86](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/4%E3%80%81RBAC%E6%9D%83%E9%99%90%E7%AE%A1%E7%90%86.md#rbac%E6%9D%83%E9%99%90%E7%AE%A1%E7%90%86)



# 三、redis管理令牌

实现方式参考：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#21redis%E7%AE%A1%E7%90%86%E4%BB%A4%E7%89%8C](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#21redis%E7%AE%A1%E7%90%86%E4%BB%A4%E7%89%8C)

## 3.1、测试

首先检查一下redis里面有哪些数据，我们才好判断token的数据到底保存到redis里面去了没有。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709141812810.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



使用什么那种登录方式都可以，但是这里我就使用密码模式登录，测试一下，能不能将token的相关信息存入redis里面去。

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



![在这里插入图片描述](https://img-blog.csdnimg.cn/202007091420312.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



## 3.2、常见的错误

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709141522459.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



连接redis失败