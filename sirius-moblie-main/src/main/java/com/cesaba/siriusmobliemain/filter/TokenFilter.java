package com.cesaba.siriusmobliemain.filter;


import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    public static final String TOKEN_KEY = "token";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Long MINUTES_10 = 10 * 60 * 1000L;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
        String token = getToken(request);
        if (StringUtils.isNotBlank(token)){
            JwtUser jwtUser = tokenService.getJwtUser(token);
            if(jwtUser != null){
                jwtUser = checkLoginTime(jwtUser);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser,
                        null, jwtUser.getAuthorities());
                System.out.println(jwtUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }


    //10分钟之内刷新token
    private JwtUser checkLoginTime(JwtUser jwtUser){
        long expireTime = jwtUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MINUTES_10){
            String token = jwtUser.getToken();
            jwtUser = (JwtUser) userDetailsService.loadUserByUsername(jwtUser.getId());
            jwtUser.setToken(token);
        }

        return jwtUser;
    }

    public static String getToken(HttpServletRequest request){
        String token = request.getParameter(TOKEN_KEY);
        if (StringUtils.isBlank(token)){
            token = request.getHeader(TOKEN_KEY);
        }

        return token;
    }

}
