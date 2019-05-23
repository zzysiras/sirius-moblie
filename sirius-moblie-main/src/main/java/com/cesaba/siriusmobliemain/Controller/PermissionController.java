package com.cesaba.siriusmobliemain.Controller;


import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.entity.Permission;
import com.cesaba.siriusmobliemain.entity.Role;
import com.cesaba.siriusmobliemain.mapper.PermissionMapper;
import com.cesaba.siriusmobliemain.service.PermissionService;
import com.cesaba.siriusmobliemain.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/current")
    public List<Permission> permissionsCurrent() {
        JwtUser jwtUser = UserUtil.getLoginUser();
        List<Permission> list = jwtUser.getPermissions();
        final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1))
                .collect(Collectors.toList());

		//setChild(permissions);
		//return permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });

        return firstLevel;
    }


    private void setChild(Permission p, List<Permission> permissions) {
        List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
        p.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }

    private void setPermissionsList(Long pId, List<Permission> permissionsAll, List<Permission> list) {
        for (Permission per : permissionsAll) {
            if (per.getParentId().equals(pId)) {
                list.add(per);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    setPermissionsList(per.getId(), permissionsAll, list);
                }
            }
        }
    }

    @GetMapping("/owns")
    public Set<String> ownsPermission() {
        List<Permission> permissions = UserUtil.getLoginUser().getPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptySet();
        }

        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(Permission::getPermission).collect(Collectors.toSet());
    }
}
