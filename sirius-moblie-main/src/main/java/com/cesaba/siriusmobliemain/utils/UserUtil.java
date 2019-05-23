package com.cesaba.siriusmobliemain.utils;

import com.cesaba.siriusmobliemain.dto.JwtUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static JwtUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (JwtUser) authentication.getPrincipal();
            }
        }

        return null;
    }
}
