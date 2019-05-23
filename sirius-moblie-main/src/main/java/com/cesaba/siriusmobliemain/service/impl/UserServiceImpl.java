package com.cesaba.siriusmobliemain.service.impl;

import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.dto.UserIp;
import com.cesaba.siriusmobliemain.entity.User;
import com.cesaba.siriusmobliemain.mapper.UserMapper;
import com.cesaba.siriusmobliemain.service.SysLogsService;
import com.cesaba.siriusmobliemain.service.TokenService;
import com.cesaba.siriusmobliemain.service.UserService;
import com.cesaba.siriusmobliemain.utils.UserUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private SysLogsService sysLogsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;


    @Override
    public User getUser(String id){return userMapper.getUser(id);}

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void insertUser(User user){
        userMapper.insertUser(user);
    }

    @Override
    public void deleteUserById(String id){
        userMapper.deleteUserById(id);
    }

    @Override
    public int updateUserById(User user){
        return userMapper.updateUserById(user);
    }

    @Override
    public int updateDept(String id, String dept) {return userMapper.updateDept(id, dept);}

    @Override
    public User selectById(String id){
        return userMapper.selectById(id);
    }

    @Override
    public List<User> selectAll(int pageNum, int pageSize){
        return userMapper.selectAllUser();
    }

    @Override
    public List<User> selectUserByDept(String deptname){
        return userMapper.selectUserByDept(deptname);
    }

    @Override
    public void changePassword(String id, String oldPassword, String newPassword) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("旧密码错误");
        }

        userMapper.changePassword(user.getId(), passwordEncoder.encode(newPassword));

    }

    @Override
    public void setPassword(String id, String newPassword) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        userMapper.changePassword(user.getId(), passwordEncoder.encode(newPassword));

    }

    @Override
    @Transactional
    public User updateUser(JwtUser jwtUser) {
        userMapper.update(jwtUser);
        sysLogsService.save(jwtUser.getId(), "更新用户", true, null);
        return jwtUser;
    }


    @Override
    public boolean saveUserIp(String ip, String token){
        String currentUserId = UserUtil.getLoginUser().getId();
        int count = userMapper.checkUserIp(currentUserId);
        JwtUser currentUser = UserUtil.getLoginUser();
        UserIp userIp = userMapper.checkUser(currentUserId, ip);//用户-IP是否存在
        if(count == 0){
            //用户首次登录系统
            //在这里更新redis中的当前用户登录IP并记录MYSQL
            //JwtUser jwtUser = redisTemplate.
            userMapper.saveUserIp(currentUserId, ip);
            currentUser.setIPaddress(ip);
            tokenService.updateJwtUser(currentUser);
            sysLogsService.save(currentUserId, "首次登录", true, null);
            return true;
        }
        if (count > 0){
            //非首次登录 判断当前IP是否出现在MYSQL中
            if (userIp!=null){
                System.out.println("已有相同的登录记录");
                currentUser.setIPaddress(ip);
                tokenService.updateJwtUser(currentUser);
                //已有该记录 数据库不做任何修改 redis里面检查更新
                return true;
            }
            else {
                System.out.println("异常IP登陆");
                tokenService.deleteToken(token);
                //执行删除redis中当前登录用户TOKEN  踢人操作
                userMapper.saveUserIp(currentUserId, ip);//记录下这个新IP
                sysLogsService.save(currentUserId, "异地登录", true, null);
                return false;
            }

        }
        return true;
    }




}
