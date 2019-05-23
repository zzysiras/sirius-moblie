package com.cesaba.siriusmobliemain.dto;

import java.util.Random;

public class VerifyString {


    private String codes = "0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    private Random r = new Random();
    private String str;

    public VerifyString(){

    }

    private char randomChar(){
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    public String getStr(){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<6;i++){
            String s = randomChar()+"";
            sb.append(s);
        }
        this.str = sb.toString();
        return str;
    }

}
