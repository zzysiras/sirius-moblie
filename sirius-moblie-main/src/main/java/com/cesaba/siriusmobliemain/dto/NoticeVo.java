package com.cesaba.siriusmobliemain.dto;

import com.cesaba.siriusmobliemain.entity.Notice;
import com.cesaba.siriusmobliemain.entity.User;

import java.io.Serializable;
import java.util.List;

public class NoticeVo implements Serializable {

    private static final long serialVersionUID = 7363353918096951799L;

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private Notice notice;

    private List<User> users;
}
