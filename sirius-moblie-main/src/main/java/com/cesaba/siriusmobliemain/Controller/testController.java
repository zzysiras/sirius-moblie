package com.cesaba.siriusmobliemain.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Controller
public class testController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @ResponseBody
    @GetMapping("/query/user")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public List<Map<String, Object>> map(){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select *from user");
        return list;
    }


    @RequestMapping("/query/user/json")
    @ResponseBody
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public String getJSON(){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select *from user");
        int count = list.size();
        JSONArray json = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",count);
        jsonObject.put("data",list);
        return jsonObject.toString();
    }


}
