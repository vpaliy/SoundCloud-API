package com.vpaliy.soundcloud.model;

public class CommentEntity {
    public String id;
    public String uri;
    public String created_at;
    public String body;
    public String timestamp;
    public String user_id;
    public MiniUserEntity user;
    public String track_id;

    public CommentEntity(){}
}
