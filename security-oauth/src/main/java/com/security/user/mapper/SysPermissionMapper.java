package com.security.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.user.model.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限
 * @author: stars
 * @date 2020年 07月 09日 12:00
 **/
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> selectPermissionByUserId(@Param("userId") Long userId);

}
