package com.cesaba.siriusmobliemain.entity;

public class Index extends BaseEntity<Long>{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    private String name;
    private String ImgUrl;
    private String words;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;


}
