package com.cesaba.siriusmobliemain.service.impl;

import com.cesaba.siriusmobliemain.entity.Syslogs;
import com.cesaba.siriusmobliemain.entity.User;
import com.cesaba.siriusmobliemain.mapper.SysLogsMapper;
import com.cesaba.siriusmobliemain.service.SysLogsService;
import freemarker.template.utility.DateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

@Service
public class SysLogsServiceImpl implements SysLogsService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SysLogsMapper sysLogsMapper;

    @Async
    @Override
    public void save(Syslogs syslogs){
        if (syslogs == null || syslogs.getUser() == null || syslogs.getUser().getId() == null){
            return;
        }

        sysLogsMapper.save(syslogs);
    }

    @Async
    @Override
    public void save(String userId , String module, Boolean flag, String remark){

        Syslogs syslogs = new Syslogs();
        syslogs.setFlag(flag);
        syslogs.setModule(module);
        syslogs.setRemark(remark);

        User user = new User();
        user.setId(userId);
        syslogs.setUser(user);

        sysLogsMapper.save(syslogs);

    }

    @Override
    public void deleteLogs(){
        Date date = DateUtils.addMonths(new Date(), -3);
        String time = DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());

        int n = sysLogsMapper.deleteLogs(time);
        log.info("删除{}之前的记录{}条" , time , n);
    }






}
