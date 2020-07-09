package com.security.user.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis-plus配置类
 * <p>`@EnableTransactionManagement`开启事务支持。实例化`PaginationInterceptor`类，并注入容器，这就分页才会有效。</p>
 * @author: stars
 * @date 2020年 07月 09日 11:42
 **/
@Configuration
@EnableTransactionManagement
public class MybatisPlusAutoConfigure {

    /***
     * 分页插件，自动识别数据库类型
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}