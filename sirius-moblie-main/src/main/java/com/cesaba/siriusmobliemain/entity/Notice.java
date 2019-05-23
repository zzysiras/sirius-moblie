package com.cesaba.siriusmobliemain.entity;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Date;

public class Notice {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private String title;
    private String content;
    private Integer status;
    private Long id;
    private Date createTime = new Date();
    private Date updateTime = new Date();

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


    public interface Status {
        int DRAFT = 0;
        int PUBLISH = 1;
    }

}
