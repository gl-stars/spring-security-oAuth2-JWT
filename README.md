# spring-security-oAuth2-JWT

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

# 二、版本说明

例如：6.0.3.release

6：主版本号

当前的功能模块,有较大的变动,比如增加了一些模块,或者整个架构发生了改变了，表示对项目的改动很大。

`0`：次版本号，局部的变动，但是变动不是很大，可能与之前的版本不兼容。 

`3`：bug的修复

- 后面的字母表示当前处于什么开发阶段。

`Base`：当前处于设计阶段，没有功能实现。

`Alpha`：初级开发阶段，还存在很严重的bug。

`Beta`：相对Alpha有很大的改动，还在需要大量的测试。

`RELEASE`：最终版本。



# 三、将jar发布到本地仓库

## 3.1、发行讲解

命令

```shell
mvn install:install-file -Dfile=E:\security-oauth-1.0.0.Beta.jar -DgroupId=com.core -DartifactId=security-oauth -Dversion=1.0.0.Beta -Dpackaging=jar
```

> -Dfile  ：需要发布到本地仓库的jar包全绝对路径
>
> -DgroupId：jar包发行主体域名
>
> -DartifactId：产品分包
>
> -Dversion：版本号
>
> -Dpackaging：文件类型

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200711195917249.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)

在本地仓库检查是否存在

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200711200140815.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxODUzNDQ3,size_16,color_FFFFFF,t_70)



## 3.2、这个jar编写pom.xml依赖注意

如果这个jar包的工程没有打成jar放到本地仓库或者私服引用，那么其他功能模块这个jar，这个jar应用的所有依赖都可以使用的。但是打成jar之后，放到本地仓库，在引入项目的这种方式，这个jar的依赖的那些依赖是不能使用。例如：在这个自定义的jar中依赖了链接数据库的所有东西，但是将这个jar引入进来之后，链接数据库的那些依赖只能在自定义这个jar中使用，不能再其他工程中使用了。