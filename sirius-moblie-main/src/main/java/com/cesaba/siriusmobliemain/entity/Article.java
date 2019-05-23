package com.cesaba.siriusmobliemain.entity;

public class Article extends BaseEntity<Long> {

    private String title;
    private String writer;
    private Integer status;
    private String type;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public interface Status {
        int DRAFT = 0;
        int PUBLISH = 1;
    }



}
