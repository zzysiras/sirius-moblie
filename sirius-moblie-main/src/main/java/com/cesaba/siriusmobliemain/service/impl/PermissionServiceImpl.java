package com.cesaba.siriusmobliemain.service.impl;

import com.cesaba.siriusmobliemain.entity.Permission;
import com.cesaba.siriusmobliemain.mapper.PermissionMapper;
import com.cesaba.siriusmobliemain.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void save(Permission permission){
        permissionMapper.save(permission);
    }

    @Override
    public void update(Permission permission){
        permissionMapper.update(permission);
    }

    @Override
    public List<Permission> findAll(){return permissionMapper.findAll();}


    @Override
    @Transactional  //删除权限逻辑有点多暂时没写
    public void delete(Long id){
        permissionMapper.delete(id);
        //PermissionMapper.deleteRolePermission(id);
        //PermissionMapper.deleteByParentId(id);

    }
}
