package com.vpaliy.soundcloud.model;

public class MyActivity {

    public String type;
    public String created_at;
    public String tags;

    public Origin origin;

    public class Origin{
        public Track track;
        public Comment comment;
        public MiniUser miniUser;
    }
}
