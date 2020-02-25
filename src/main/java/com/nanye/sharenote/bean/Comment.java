package com.nanye.sharenote.bean;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private String content;
    private String createdTime;
    private Integer noteid;
    private String openid;
    private String nickName;
    private String avatarUrl;
}
