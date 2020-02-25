package com.nanye.sharenote.bean;

import lombok.Data;

@Data
public class Note {
    private Integer id;
    private Integer star;
    private String openid;
    private String title;
    private String category;
    private String info;
    private String nickName;
    private String avatarUrl;
    private String createdTime;
    private Integer pageViews;
}
