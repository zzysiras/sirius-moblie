package com.cesaba.siriusmobliemain.mapper;


import com.cesaba.siriusmobliemain.entity.Index;
import com.cesaba.siriusmobliemain.entity.Video;
import io.swagger.models.auth.In;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IndexMapper {


    @Select("select * from labweb where name = #{name}")
    Index selectByName(String name);

    @Select("select * from labweb")
    List<Index> selectAll();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into labweb(name, ImgUrl, path, words) values (#{name}, #{ImgUrl}, #{path}, #{words})")
    int set(Index index);

    @Delete("delete from labweb where name=#{name}")
    int deteleByName(String name);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into videolist(videoname, url, path) values (#{videoname}, #{url}, #{path})")
    int saveVideo(Video video);

    @Delete("delete from videolist where id=#{id}")
    int deleteVideo(Long id);

    List<Video> listvideo(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    int countvideo(@Param("params") Map<String, Object> params);

    @Select("select * from videolist where id = #{id}")
    Video getVideoById(Long id);


}
