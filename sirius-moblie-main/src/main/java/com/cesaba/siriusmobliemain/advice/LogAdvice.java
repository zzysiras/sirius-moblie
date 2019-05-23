package com.cesaba.siriusmobliemain.advice;


import com.cesaba.siriusmobliemain.annotation.LogAnnotation;
import com.cesaba.siriusmobliemain.entity.Syslogs;
import com.cesaba.siriusmobliemain.mapper.SysLogsMapper;
import com.cesaba.siriusmobliemain.service.SysLogsService;
import com.cesaba.siriusmobliemain.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {

    @Autowired
    private SysLogsService sysLogsService;

    @Around(value = "@annotation(com.cesaba.siriusmobliemain.annotation.LogAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable{
        Syslogs syslogs = new Syslogs();
        syslogs.setUser(UserUtil.getLoginUser());
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String module = null;

        LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
        module = logAnnotation.module();
        if (StringUtils.isEmpty(module)){
            ApiOperation apiOperation = methodSignature.getMethod().getDeclaredAnnotation(ApiOperation.class);
            if (apiOperation != null ){
                module = apiOperation.value();
            }
        }

        if (org.springframework.util.StringUtils.isEmpty(module)) {
            throw new RuntimeException("没有指定日志module");
        }
        syslogs.setModule(module);

        try {
            Object object = joinPoint.proceed();
            syslogs.setFlag(true);
            return object;
        }catch (Exception e){
            syslogs.setFlag(false);
            syslogs.setRemark(e.getMessage());
            throw e;
        }finally {
            if (syslogs.getUser() != null){
                sysLogsService.save(syslogs);
            }
        }
    }

}
