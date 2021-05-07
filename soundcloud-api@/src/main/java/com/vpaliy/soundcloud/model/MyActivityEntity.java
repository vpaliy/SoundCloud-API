package com.vpaliy.soundcloud.model;

public class MyActivityEntity {

    public String type;
    public String created_at;
    public String tags;

    public Origin origin;

    public MyActivityEntity() {
    }

    public class Origin {
        public TrackEntity track;
        public CommentEntity comment;
        public MiniUserEntity miniUser;

        public Origin() {
        }
    }
}
