package com.security.user;

import com.security.user.model.SysPermission;
import com.security.user.service.SysPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: stars
 * @date 2020年 07月 09日 13:10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthServerApplication {

    /**
     * 在 SpringSecurityBean 添加到容器了
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取权限信息
     */
    @Autowired(required = false)
    SysPermissionService sysPermissionService;

    @Test
    public void findPermission(){

        List<SysPermission> list = sysPermissionService.list();
        System.out.println(list);
    }

    @Test
    public void testPasswordencoder(){
        String encode = passwordEncoder.encode("ssoj-secret");

        System.out.println(encode);

        System.out.println(passwordEncoder.encode("123456"));
    }
}
