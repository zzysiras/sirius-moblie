package com.cesaba.siriusmobliemain.service.impl;

import com.cesaba.siriusmobliemain.entity.Dept;
import com.cesaba.siriusmobliemain.mapper.DeptMapper;
import com.cesaba.siriusmobliemain.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void insertDept(Dept dept){
        deptMapper.insertDept(dept);
    }

    @Override
    public void insertDeptByDeptName(String deptname) {deptMapper.insertDeptByDeptName(deptname);}

    @Override
    public void deleteDeptByDeptName(String deptname){
        deptMapper.deleteDeptByDeptName(deptname);
    }

    @Override
    public int updateDept(Dept dept){
        return deptMapper.updateDept(dept);
    }

    @Override
    public Dept selectByDeptName(String deptname){
        return deptMapper.selectByDeptName(deptname);
    }

}
