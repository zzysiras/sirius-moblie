package com.cesaba.siriusmobliemain.mapper;


import com.cesaba.siriusmobliemain.dto.NoticeReadVO;
import com.cesaba.siriusmobliemain.entity.Notice;
import com.cesaba.siriusmobliemain.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    @Select("select * from notice t where t.id = #{id}")
    Notice getById(Long id);

    @Delete("delete from notice where id = #{id}")
    int delete(Long id);

    @Update("update notice t set title = #{title}, content = #{content}, status = #{status}, updateTime = #{updateTime} where t.id = #{id}")
    int update(Notice notice);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into notice(title, content, status, createTime, updateTime) values(#{title}, #{content}, #{status}, #{createTime}, #{updateTime})")
    int save(Notice notice);

    int count(@Param("params")Map<String, Object> params);

    List<Notice> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                      @Param("limit") Integer limit);

    @Insert("insert Ignore into notice_read(noticeId, id, createTime) values(#{noticeId}, #{id}, now())")
    int saveReadRecord(@Param("noticeId") Long noticeId, @Param("id") String id);

    List<User> listReadUsers(Long noticeId);

    @Select("select count(1) from notice t left join notice_read r on r.noticeId = t.id and r.id = #{id} where t.status = 1 and r.id is null")
    int countUnread(String userId);

    int countNotice(@Param("params") Map<String, Object> params);

    List<NoticeReadVO> listNotice(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                                  @Param("limit") Integer limit);





}
