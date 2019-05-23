package com.cesaba.siriusmobliemain.Controller;


import com.cesaba.siriusmobliemain.entity.Syslogs;
import com.cesaba.siriusmobliemain.mapper.SysLogsMapper;
import com.cesaba.siriusmobliemain.tables.PageTableHandler;
import com.cesaba.siriusmobliemain.tables.PageTableRequest;
import com.cesaba.siriusmobliemain.tables.PagesTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "日志")
@RestController
@RequestMapping("/logs")
public class sysLogsController {

    @Autowired
    private SysLogsMapper syslogsMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('sys:user:password')")
    @ApiOperation(value = "日志列表")
    public PagesTableResponse list(PageTableRequest request){
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return syslogsMapper.count(request.getParams()); }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<Syslogs> list(PageTableRequest request) {
                return syslogsMapper.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }
}
