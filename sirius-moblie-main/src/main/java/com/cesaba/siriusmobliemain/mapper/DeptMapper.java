package com.cesaba.siriusmobliemain.mapper;

import com.cesaba.siriusmobliemain.entity.Dept;
import org.springframework.stereotype.Repository;


@Repository
public interface DeptMapper {


    int insertDept(Dept dept);

    int insertDeptByDeptName(String deptname);

    int deleteDeptByDeptName(String deptname);

    int updateDept(Dept dept);

    Dept selectByDeptName(String deptname);
}
