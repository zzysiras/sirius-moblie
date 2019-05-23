package com.cesaba.siriusmobliemain.service;

import com.cesaba.siriusmobliemain.entity.Dept;

public interface DeptService {

    public void insertDept(Dept dept);

    public void deleteDeptByDeptName(String deptname);

    public int updateDept(Dept dept);

    public Dept selectByDeptName(String dept);

    public void insertDeptByDeptName(String deptname);

}
