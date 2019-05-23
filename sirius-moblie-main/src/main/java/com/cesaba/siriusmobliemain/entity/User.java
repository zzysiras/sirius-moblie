package com.cesaba.siriusmobliemain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {

    private String grade;
    private String id;
    private String username;
    private String password;
    private String fullorpart;
    private String acaorpro;
    private String headImgUrl;
    private String major;
    private String phonenumber;
    private String email;
    private String sex;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    private String roleid;



    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;
    private String dept;
    private String issuper;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullorpart() {
        return fullorpart;
    }

    public void setFullorpart(String fullorpart) {
        this.fullorpart = fullorpart;
    }

    public String getAcaorpro() {
        return acaorpro;
    }

    public void setAcaorpro(String acaorpro) {
        this.acaorpro = acaorpro;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIssuper() {
        return issuper;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


    public void setIssuper(String issuper) {
        this.issuper = issuper;
    }



}
