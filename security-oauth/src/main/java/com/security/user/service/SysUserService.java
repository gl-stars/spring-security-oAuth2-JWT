package com.security.user.service;

import com.security.user.model.SysUser;

/**
 * 实现 IService<T> 接口，提供了常用更复杂的对 T 数据表的操作，
 * <p> 比如：支持 Lambda 表达式，批量删除、自动新增或更新操作</p>
 * @author: stars
 * @date 2020年 07月 09日 11:50
 **/
public interface SysUserService {

    /**
     * 通过用户名查询
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUsername(String username);

    /**
     * 通过手机号查询
     * @param mobile 手机号
     * @return 用户信息
     */
    SysUser findByMobile(String mobile);
}
