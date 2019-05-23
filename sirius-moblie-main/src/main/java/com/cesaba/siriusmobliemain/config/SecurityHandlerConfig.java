package com.cesaba.siriusmobliemain.config;


import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.dto.ResponseInfo;
import com.cesaba.siriusmobliemain.dto.Token;
import com.cesaba.siriusmobliemain.filter.TokenFilter;
import com.cesaba.siriusmobliemain.service.TokenService;
import com.cesaba.siriusmobliemain.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityHandlerConfig {

    @Autowired
    private TokenService tokenService;

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
                Token token = tokenService.saveToken(jwtUser);
                ResponseUtil.responseJson(httpServletResponse, HttpStatus.OK.value(), token);
                System.out.println("登录成功");
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler(){
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                String msg = null;
                if (e instanceof BadCredentialsException){
                    msg = "密码错误";
                }else {
                    msg = e.getMessage();
                }
                ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", msg);
                ResponseUtil.responseJson(httpServletResponse, HttpStatus.UNAUTHORIZED.value(), info);
            }
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", "请登录");
                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
                System.out.println("有东西没写对");
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                ResponseInfo info = new ResponseInfo(HttpStatus.OK.value() + "", "退出成功");
                String token = TokenFilter.getToken(httpServletRequest);
                tokenService.deleteToken(token);

                ResponseUtil.responseJson(httpServletResponse, HttpStatus.OK.value(), info);
            }
        };
    }
}
