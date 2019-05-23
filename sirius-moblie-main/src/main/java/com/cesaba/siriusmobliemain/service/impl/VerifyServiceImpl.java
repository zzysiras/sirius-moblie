package com.cesaba.siriusmobliemain.service.impl;

import com.cesaba.siriusmobliemain.dto.VerifyCode;
import com.cesaba.siriusmobliemain.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;


@Service
@Component
public class VerifyServiceImpl implements VerifyService {


    public static final long DEFAULT_EXPIRE = 60 * 30;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void mail_verify(String id , String str){
        if(existsKey(id)){
            redisTemplate.delete(id);
            redisTemplate.opsForValue().set(id, str);
        }else {
            redisTemplate.opsForValue().set(id, str);
        }
    }

    @Override
    public boolean identify(String id, String str) throws NullPointerException{
        if (id!=null){
            String answer = redisTemplate.opsForValue().get(id);
            if (answer.equals(str)){
                //redisTemplate.delete(id);
                return true;
            }else {
                //redisTemplate.delete(id);
                return false;
            }
        }else{
            return false;
        }
    }




}
