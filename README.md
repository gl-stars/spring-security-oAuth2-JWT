# spring-security-oAuth2-JWT

使用详细说明看：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2%E3%80%81SpringSecurityOAuth2%E8%AE%A4%E8%AF%81.md](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/2、SpringSecurityOAuth2认证.md)

这个文档在：https://github.com/gl-stars/springSecurity-example项目中

# 一、分支说明

| 分支   | 描述                                 |
| ------ | ------------------------------------ |
| master | 最终需要采用的方案                   |
| v1     | 完成授权码模式                       |
| v2     | 密码模式登录                         |
| v3     | 简化和客服端授权模式                 |
| v4     | 实现中文提示信息，刷新token          |
| v5     | 新增RBAC权限管理和redis管理令牌      |
| v6     | 新增JDBC管理令牌和授权码保存到数据中 |



# 二、JDBC管理令牌

实现方式参考：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#22jdbc%E7%AE%A1%E7%90%86%E4%BB%A4%E7%89%8C](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#22jdbc%E7%AE%A1%E7%90%86%E4%BB%A4%E7%89%8C)

将`token`相关信息保存在数据库中，其实只需要更改“令牌管理方式”就行了。其余数据库相关jar或数据库相关配置就不用说了，都需要连接数据库，哪些东西是必不可少的。上面文档里面写的详细一些，但是这个地方直接这样写就行，我截图下来了。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709145039620.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



## 2.1、测试

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

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709145428911.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

# 三、将授权码保存到数据库中

貌似这样干是没有效果的，但是有些程序采用授权码模式登陆还是有必要。例如建设银行手机APP第一次登陆的时候，发短信过来的那个就是授权码，不是短信验证码。

实现方法：[https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E4%B8%89%E5%B0%86%E6%8E%88%E6%9D%83%E7%A0%81%E4%BF%9D%E5%AD%98%E5%88%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E4%B8%AD](https://github.com/gl-stars/springSecurity-example/blob/master/sse-doc/3%E3%80%81%E9%85%8D%E7%BD%AE%E8%AE%A4%E8%AF%81%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AD%96%E7%95%A5.md#%E4%B8%89%E5%B0%86%E6%8E%88%E6%9D%83%E7%A0%81%E4%BF%9D%E5%AD%98%E5%88%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E4%B8%AD)



## 3.1、测试

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

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709152557308.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)