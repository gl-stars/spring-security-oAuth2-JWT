package com.security.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限信息
 * @author: stars
 * @date 2020年 07月 09日 11:59
 **/
@Data
public class SysPermission implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父资源id,给它初始值 0
     * 新增和修改页面上默认的父资源id
     */
    private Long parentId = 0L ;
    /**
     * 用于新增和修改页面上默认的根菜单名称
     */
    @TableField(exist = false)
    private String parentName = "根菜单";

    /**
     * 名称
     */
    private String name;

    private String code;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 菜单：1，按钮：2
     */
    private Integer type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更改时间
     */
    private Date updateDate;


    /**
     * 所有子权限对象集合
     * 左侧菜单渲染时要用
     */
    @TableField(exist = false)
    private List<SysPermission> children;

    /**
     * 所有子权限 URL 集合
     * 左侧菜单渲染时要用
     */
    @TableField(exist = false)
    private List<String> childrenUrl;
}
