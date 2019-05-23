package com.cesaba.siriusmobliemain.mapper;

import com.cesaba.siriusmobliemain.entity.Syslogs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysLogsMapper {

    @Insert("insert into syslogs(userId, module, flag, remark, createTime) values(#{user.id}, #{module}, #{flag}, #{remark}, now())")
    int save(Syslogs syslogs);

    int count(@Param("params") Map<String, Object> params);

    List<Syslogs> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    @Delete("delete from syslogs where createTime <= #{time}")
    int deleteLogs(String time);

}
