package com.cesaba.siriusmobliemain.entity;

import java.util.Date;

public class Dict {
    private static final long serialVersionUID = -2431140186410912787L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    private String type;
    private String k;
    private String val;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Long id;
    private Date createTime = new Date();
    private Date updateTime = new Date();
}
