package com.cesaba.siriusmobliemain.service;

import com.cesaba.siriusmobliemain.dto.JwtUser;
import com.cesaba.siriusmobliemain.dto.Token;

public interface TokenService {

    Token saveToken(JwtUser jwtUser);

    void refresh(JwtUser jwtUser);

    JwtUser getJwtUser(String token);

    boolean deleteToken(String token);

    void updateJwtUser(JwtUser jwtUser);
}
