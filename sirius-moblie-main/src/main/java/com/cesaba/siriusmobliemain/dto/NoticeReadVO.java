package com.cesaba.siriusmobliemain.dto;

import com.cesaba.siriusmobliemain.entity.Notice;

import java.util.Date;

public class NoticeReadVO extends Notice {

    private static final long serialVersionUID = -3842182350180882396L;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    private Long userId;
    private Date readTime;
    private Boolean isRead;
}
