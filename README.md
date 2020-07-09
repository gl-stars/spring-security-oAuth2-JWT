# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述               |
| ------ | ------------------ |
| master | 最终需要采用的方案 |
| v1     | 完成授权码模式     |

​	

# 二、测试

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090514365.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070909064782.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090734155.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



## postman测试关键词

```
grant_type
password
username
password
```

# postman测试是需要的问题




![在这里插入图片描述](https://img-blog.csdnimg.cn/20200708180342317.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

注意配置授权码模式的时候，仔细检查是否又密码授权码模式，第二个原因是 `grant_type`仔细看一下是否为 `password`。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709090232415.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



