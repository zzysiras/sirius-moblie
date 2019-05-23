package com.cesaba.siriusmobliemain.mapper;


import com.cesaba.siriusmobliemain.entity.Article;
import com.cesaba.siriusmobliemain.entity.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    @Select("select * from article where id = #{id}")
    Article getById(Long id);

    @Delete("delete from article where id = #{id}")
    int delete(Long id);

    @Update("update article t set title = #{title}, content = #{content}, writer = #{writer}, type = #{type}, updateTime = #{updateTime} where t.id = #{id}")
    int update(Article article);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into article(title, type, writer, content, createTime, updateTime) values(#{title}, #{type}, #{writer}, #{content},  #{createTime}, #{updateTime})")
    int save(Article article);

    int count(@Param("params") Map<String, Object> params);

    List<Article> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                      @Param("limit") Integer limit);

    @Select("select * from article  where type = #{type}")
    List<Article> getListByType(@PathVariable String type);
}
