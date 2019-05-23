package com.cesaba.siriusmobliemain.entity;

import java.util.List;

public class Permission {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private Long parentId;
    private String name;

    public List<Permission> getChild() {
        return child;
    }

    public void setChild(List<Permission> child) {
        this.child = child;
    }

    private List<Permission> child;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Integer type;
    private String href;
    private String permission;


    private static final long serialVersionUID = 6180869216498363919L;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }







}
