package com.security.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * @author: stars
 * @date 2020年 07月 09日 11:45
 **/
@Data
public class SysUser implements UserDetails {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码，加密存储
     */
    private String password;

    /**
     * 帐户是否过期(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     *
     * 注意：生成的setter和getter方法没有 `is`
     * setAccountNonExpired
     * getAccountNonExpired
     * 所以前端获取时也不要有 `is`
     */
    private boolean isAccountNonExpired = true;

    /**
     * 帐户是否被锁定(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isAccountNonLocked = true;

    /**
     * 密码是否过期(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isCredentialsNonExpired = true;

    /**
     * 帐户是否可用(true(1) 可用，false(0)未删除)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isEnabled = true;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后更改时间
     */
    private Date updateDate;

    /**
     * 拥有权限集合
     * @TableField(exist = false) 该属性不是数据库表字段
     */
    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 父接口认证方法 start
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    /**
     * 拥有角色集合
     */
    @TableField(exist = false)
    private List<SysRole> roleList = new ArrayList<SysRole>();

    /**
     * 获取所有角色id
     */
    @TableField(exist = false)
    private List<Long> roleIds = new ArrayList<Long>();
    public List<Long> getRoleIds() {
        if(CollectionUtils.isNotEmpty(roleList)) {
            roleIds = new ArrayList<Long>();
            for(SysRole role : roleList) {
                roleIds.add(role.getId());
            }
        }
        return roleIds;
    }

    @TableField(exist = false)
    private List<SysPermission> permissions = new ArrayList<SysPermission>();
}
