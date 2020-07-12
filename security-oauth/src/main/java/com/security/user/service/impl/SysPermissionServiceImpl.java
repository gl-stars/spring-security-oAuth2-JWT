package com.security.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.user.mapper.SysPermissionMapper;
import com.security.user.model.SysPermission;
import com.security.user.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限
 * @author: stars
 * @date 2020年 07月 09日 12:02
 **/
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    /**
     * 通过用户id查询所拥有权限
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> findByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        List<SysPermission> permissionList = baseMapper.selectPermissionByUserId(userId);
        // 如果没有权限，则将集合中的数据null移除
//        permissionList.remove(null);
        return permissionList;
    }

}