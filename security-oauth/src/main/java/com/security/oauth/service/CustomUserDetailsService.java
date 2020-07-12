package com.security.oauth.service;

import com.security.user.model.SysUser;
import com.security.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author: stars
 * @date 2020年 07月 09日 10:27
 **/
@Component("customUserDetailsService")
public class CustomUserDetailsService extends AbstractUserDetailsService {

    /**
     * 注入用户信息
     */
    @Autowired
    private SysUserService sysUserService;


    @Override
    SysUser findSysUser(String usernameOrMobile){
        return sysUserService.findByUsername(usernameOrMobile);
    }
}
