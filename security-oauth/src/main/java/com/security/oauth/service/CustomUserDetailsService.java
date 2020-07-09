package com.security.oauth.service;

import com.security.user.model.SysUser;
import com.security.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


//    @Autowired
//    private PasswordEncoder passwordEncoder ;

  /*  *//**
     * 每次登录都会调用这个方法验证用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     *//*
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("admin", passwordEncoder.encode("123456"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("product"));
    }*/

    @Override
    SysUser findSysUser(String usernameOrMobile){
        return sysUserService.findByUsername(usernameOrMobile);
    }
}
