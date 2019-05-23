package com.cesaba.siriusmobliemain.entity;

import com.cesaba.siriusmobliemain.dto.JwtUser;

public class Syslogs extends BaseEntity<Long> {

    private static final long serialVersionUID = -7809315432127036583L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private User user;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
    private String module;
    private Boolean flag;
    private String remark;
}
