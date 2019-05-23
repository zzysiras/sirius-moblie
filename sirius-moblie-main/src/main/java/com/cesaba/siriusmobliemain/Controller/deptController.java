package com.cesaba.siriusmobliemain.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesaba.siriusmobliemain.annotation.LogAnnotation;
import com.cesaba.siriusmobliemain.entity.Dept;
import com.cesaba.siriusmobliemain.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "分组")
@RestController
@RequestMapping("/dept")
public class deptController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private DeptService deptService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @LogAnnotation
    @ApiOperation(value = "新建分组")
    @RequestMapping(method = RequestMethod.POST, value="/insert")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public void insert(Dept dept){
        deptService.insertDept(dept);
    }

    @LogAnnotation
    @ApiOperation(value = "自动分组")
    @RequestMapping(method = RequestMethod.POST, value = "/insert/{deptname}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public void insertDeptByDeptName(@PathVariable("deptname") String deptname){
        deptService.insertDeptByDeptName(deptname);
    }

    @LogAnnotation
    @ApiOperation(value = "删除分组")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{deptname}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public void delete(@PathVariable("deptname") String deptname){
        deptService.deleteDeptByDeptName(deptname);
    }

    @LogAnnotation
    @ApiOperation(value = "修改分组")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void update(Dept dept){
        deptService.updateDept(dept);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/select/{deptname}")
    public Dept select(@PathVariable("deptname") String deptname){
        return deptService.selectByDeptName(deptname);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:query')")
    public String getJSON() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select *from dept");
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("",list);
        String json=JSON.toJSONString(list);
        return json;
        //return jsonObject.toString();
    }



}
