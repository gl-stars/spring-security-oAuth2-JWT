package com.security.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色信息
 * @author: stars
 * @date 2020年 07月 09日 11:55
 **/
@Data
public class SysRole implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String remark;

    private Date createDate;
    private Date updateDate;

    /**
     * 存储当前角色的权限资源对象集合
     * 修改角色时用到
     */
    @TableField(exist = false)
    private List<SysPermission> perList = new ArrayList<SysPermission>();
    /**
     * 存储当前角色的权限资源ID集合
     * 修改角色时用到
     */
    @TableField(exist = false)
    private List<Long> perIds = new ArrayList<Long>();

    public List<Long> getPerIds() {
        if(CollectionUtils.isNotEmpty(perList)) {
            perIds =new ArrayList<Long>();
            for(SysPermission per : perList) {
                perIds.add(per.getId());
            }
        }
        return perIds;
    }
}