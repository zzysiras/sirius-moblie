package com.cesaba.siriusmobliemain.mapper;


import com.cesaba.siriusmobliemain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select * from role r inner join user_role ur on r.roleid = ur.roleid where ur.userid = #{userid}")
    List<Role> listByUserId(String userid);


}
