package com.cesaba.siriusmobliemain.service;

import com.cesaba.siriusmobliemain.entity.Syslogs;

public interface SysLogsService {

    void save(Syslogs syslogs);

    void save(String userId , String module, Boolean flag, String mark);

    void deleteLogs();
}
