package com.cesaba.siriusmobliemain.dto;

import com.cesaba.siriusmobliemain.entity.Permission;
import com.cesaba.siriusmobliemain.entity.Role;
import com.cesaba.siriusmobliemain.entity.User;
import com.cesaba.siriusmobliemain.mapper.RoleMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUser extends User implements UserDetails {

    private static final long serialVersionUID = -1379274258881257107L;

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    private Long loginTime;
    private Long expireTime;
    private String token;
    private List<Permission> permissions;

    public String getIPaddress() {
        return IPaddress;
    }

    public void setIPaddress(String IPaddress) {
        this.IPaddress = IPaddress;
    }

    private String IPaddress;  //当前登录IP

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }





    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        // do nothing
    }



    /*@Override
    @JsonIgnore   //这一步collect可以研究下
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        //
    }*/




    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    /*public JwtUser(User user){
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRoleid()));
    }*/


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


}
