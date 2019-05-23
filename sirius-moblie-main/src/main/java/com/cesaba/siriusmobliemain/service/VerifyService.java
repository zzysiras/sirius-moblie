package com.cesaba.siriusmobliemain.service;

public interface VerifyService {


    void mail_verify(String id, String str);

    boolean identify(String id, String str);
}
