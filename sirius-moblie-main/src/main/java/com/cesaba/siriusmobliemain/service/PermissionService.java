package com.cesaba.siriusmobliemain.service;

import com.cesaba.siriusmobliemain.entity.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> findAll();


    void save (Permission permission);

    void update(Permission permission);

    void delete(Long id);
}
