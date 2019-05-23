package com.cesaba.siriusmobliemain.service;


import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import java.util.List;

public interface UserService {

    User getUser(String id);

    User updateUser(JwtUser jwtUser);

    public void insertUser(User user);

    public void deleteUserById(String id);

    public int updateUserById(User user);

    public int updateDept(String id, String dept);

    public User selectById(String id);

    public boolean saveUserIp(String ip, String token);

    public List<User> selectAll(int pageNum, int pageSize);

    public List<User> selectUserByDept(String deptname);

    void changePassword(String id, String oldPassword, String newPassword);

    void setPassword(String id, String newPassword);
}
