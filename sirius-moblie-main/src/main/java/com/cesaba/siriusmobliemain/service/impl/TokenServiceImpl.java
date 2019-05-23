package com.cesaba.siriusmobliemain.service.impl;


import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.dto.Token;
import com.cesaba.siriusmobliemain.service.SysLogsService;
import com.cesaba.siriusmobliemain.service.TokenService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Primary   //用redis存储token
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.expire.seconds}")
    private Integer expireSeconds;

    @Value("${token.jwtSecret")
    private String jwtSecret;

    @Autowired
    private RedisTemplate<String, JwtUser> redisTemplate;

    @Autowired
    private SysLogsService sysLogsService;

    private static Key KEY = null;
    private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Override
    public Token saveToken(JwtUser jwtUser){
        jwtUser.setToken(UUID.randomUUID().toString());
        cacheJwtUser(jwtUser);
        sysLogsService.save(jwtUser.getId(), "登录" , true , null);

        String jwtToken = createJWTToken(jwtUser);

        return new Token(jwtToken, jwtUser.getLoginTime());
    }

    @Override
    public void updateJwtUser(JwtUser jwtUser)
    {
        jwtUser.setLoginTime(System.currentTimeMillis());
        jwtUser.setExpireTime(jwtUser.getLoginTime() + expireSeconds * 1000);
        redisTemplate.boundValueOps(getTokenKey(jwtUser.getToken())).set(jwtUser, expireSeconds, TimeUnit.SECONDS);
    }


    //生成jwt
    private String createJWTToken(JwtUser jwtUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_USER_KEY, jwtUser.getToken());

        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .compact();

        return jwtToken;
    }

    private void cacheJwtUser(JwtUser jwtUser){
        jwtUser.setLoginTime(System.currentTimeMillis());
        jwtUser.setExpireTime(jwtUser.getLoginTime() + expireSeconds * 1000);
        redisTemplate.boundValueOps(getTokenKey(jwtUser.getToken())).set(jwtUser, expireSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void refresh(JwtUser jwtUser) {
        cacheJwtUser(jwtUser);
    }

    @Override
    public JwtUser getJwtUser(String jwtToken){
        String uuid = getUUIDFromJWT(jwtToken);
        if (uuid != null) {
            return redisTemplate.boundValueOps(getTokenKey(uuid)).get();
        }

        return null;
    }

    @Override
    public boolean deleteToken(String jwtToken){
        String uuid = getUUIDFromJWT(jwtToken);
        if (uuid != null){
            String key = getTokenKey(uuid);
            JwtUser jwtUser = redisTemplate.opsForValue().get(key);
            if (jwtUser != null){
                redisTemplate.delete(key);
                sysLogsService.save(jwtUser.getId(), "退出", true, null);
                return true;
            }
        }
        return false;
    }

    private String getTokenKey(String uuid){
        return "tokens: " + uuid;
    }


    /**
     * 单例模式-双重锁模式
     * @return
     */
    private Key getKeyInstance(){
        if (KEY == null){

            //从这里开始同步方法
            synchronized (TokenServiceImpl.class){
                if (KEY == null){
                    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
                    KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

                }
            }
        }
        return KEY;
    }

    private String getUUIDFromJWT(String jwtToken){
        if ("null".equals(jwtToken) || StringUtils.isBlank(jwtToken)){
            return null;
        }



        try{
            Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken).getBody();
            return MapUtils.getString(jwtClaims, LOGIN_USER_KEY);
        }catch (ExpiredJwtException e){
            System.out.println("{}已过期");
        }catch (Exception e){
            System.out.println("删除失败");
            System.out.println(e);
        }
        return null;
    }






}
