package com.cesaba.siriusmobliemain.mapper;

import com.cesaba.siriusmobliemain.dto.UserIp;
import com.cesaba.siriusmobliemain.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User getUser(String id);

    int update(User user);

    int insertUser(User user);

    int deleteUserById(String id);

    int updateUserById(User user);

    int updateDept(@Param("id")String id ,@Param("dept")String dept);

    User selectById(String id);

    List<User> selectAllUser();

    @Select("select userip from user_ip where id=#{id}")
    String getUserIp(String id);

    @Select("select count(1) from user_ip where id=#{currentUserId}")
    int checkUserIp(@Param("currentUserId") String currentUserId);

    @Select("select * from user_ip where id=#{currentUserId} and userip=#{ip}")
    UserIp checkUser(@Param("currentUserId") String currentUserId, @Param("ip") String ip);

    @Insert("insert into user_ip (id, userip) values (#{currentUserId}, #{ip})")
    void saveUserIp(@Param("currentUserId") String currentUserId, @Param("ip") String ip);

    @Select("select * from user where dept=#{deptname}")
    List<User> selectUserByDept(String deptname);

    @Update("update user set password = #{password} where id = #{id}")
    int changePassword(@Param("id") String id, @Param("password") String password);

    @Update("update user set password = #{password} where id = #{id}")
    int setPassword(@Param("id") String id, @Param("password") String password);



    /*@Select("select * from user")
    public User getAllUser();

    @Options(useGeneratedKeys = false,keyProperty = "id")
    @Insert("insert into user(grade, id, username, fullorpart, acaorpro, major, sex, phonenumber, email, dept, brithday, issuper) values(#{grade}, #{id}, #{username}, #{fullorpart}, #{acaorpro}, #{major}, #{sex}, #{phonenumber}, #{email}, #{dept}, #{brithday}, #{issuper}")
    public int insertUserById(User user);

    @Delete("delete from user where id=#{id}")
    public int deleteUserById(String id);

    @Update("update user set username=#{username} where id=#{id}")
    public int updateUserById(User user);*/




}
