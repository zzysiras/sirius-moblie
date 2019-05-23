package com.cesaba.siriusmobliemain.service.impl;

import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.entity.Permission;
import com.cesaba.siriusmobliemain.entity.Role;
import com.cesaba.siriusmobliemain.entity.User;
import com.cesaba.siriusmobliemain.mapper.PermissionMapper;
import com.cesaba.siriusmobliemain.mapper.RoleMapper;
import com.cesaba.siriusmobliemain.mapper.UserMapper;
import com.cesaba.siriusmobliemain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger("adminLogger");


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userService.getUser(id);
        //应该有用户有效过期锁定等状态判断
        if (user == null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }
        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(user, jwtUser);

        List<Permission> permissions = permissionMapper.listByUserId(user.getId());

        //String ip = userMapper.getUserIp(id);


        System.out.println(permissions);
        jwtUser.setPermissions(permissions);
        //jwtUser.setIPaddress(ip);
        System.out.println(user);
        System.out.println(jwtUser.getIPaddress());

        return jwtUser;

    }
}
