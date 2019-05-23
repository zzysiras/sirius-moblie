package com.cesaba.siriusmobliemain.mapper;

import com.cesaba.siriusmobliemain.entity.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Insert("insert into permission(id, parentId, name, href, permission) values(#{id}, #{parentId}, #{name}, #{href}, #{permission}")
    int save(Permission permission);

    @Update("update permission p set id = #{id}, parentId = #{parentId}, name = #{name}, href = #{href}, permission = #{permission} where p.id = #{id}")
    int update(Permission permission);

    @Delete("delete from permission where id = #{id}")
    int delete(Long id);

    @Select("select distinct permission from permission p inner join role_permission rp on p.id = rp.permissionId inner join user_role ru on ru.roleid = rp.roleid where ru.userid = #{userid} order by p.sort")
    List<Permission> listByUserId(String userid);

    @Select("select * from permission")
    List<Permission> findAll();


}
