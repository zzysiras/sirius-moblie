package com.cesaba.siriusmobliemain.mapper;


import com.cesaba.siriusmobliemain.entity.FileInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {

    @Select("select * from file_info t where t.id = #{id}")
    FileInfo getById(String id);

    @Insert("insert into file_info(id, contentType, size, path, url, type, createTime, updateTime, originName) values(#{id}, #{contentType}, #{size}, #{path}, #{url}, #{type}, now(), now(), #{originName})")
    int save(FileInfo fileInfo);

    @Update("update file_info t set t.updateTime = now() where t.id = #{id}")
    int update(FileInfo fileInfo);

    @Delete("delete from file_info where id = #{id}")
    int delete(String id);

    int count(@Param("params") Map<String, Object> params);

    List<FileInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                        @Param("limit") Integer limit);
}
