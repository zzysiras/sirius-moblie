package com.cesaba.siriusmobliemain.mapper;

import com.cesaba.siriusmobliemain.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DictMapper {

    @Select("select * from dict t where t.type = #{type}")
    List<Dict> listByType(String type);
}
