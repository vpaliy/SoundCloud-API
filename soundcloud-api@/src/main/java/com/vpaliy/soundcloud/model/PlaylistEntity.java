package com.vpaliy.soundcloud.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class PlaylistEntity {
    public String id;
    public String created_at;
    public String user_id;
    public String duration;
    public String sharing;
    public String tag_list;
    public String permalink;
    public String track_count;

    @SerializedName("streamable")
    public boolean is_streamable;

    @SerializedName("downloadable")
    public boolean is_downloadable;
    public String embeddable_by;
    public String purchase_url;
    public String type;
    public String label;
    public String label_id;
    public String label_name;
    public String playlist_type;
    public String ean;
    public String description;
    public String genre;
    public String release;
    public String purchase_title;
    public String title;
    public String release_year;
    public String release_month;
    public String release_day;
    public String license;
    public String uri;
    public String permalink_url;
    public String artwork_url;
    public MiniUserEntity user;
    public List<TrackEntity> tracks;

    public static class PlaylistType {
        public static final String EP_SINGLE = "ep single";
        public static final String ALBUM = "album";
        public static final String COMPILATION = "compilation";
        public static final String PROJECT_FILES = "project files";
        public static final String ARCHIVE = "archive";
        public static final String SHOWCASE = "showcase";
        public static final String DEMO = "demo";
        public static final String SAMPLE_PACK = "sample pack";
        public static final String OTHER = "other";
    }

    public static class EmbeddableBy {
        public static final String ALL = "all";
        public static final String ME = "me";
        public static final String NONE = "none";
    }

    public enum  Representation{
        ID("id"),
        COMPACT("compact");
        public String name;
        Representation(String name){
            this.name=name;
        }
    }

    @SuppressWarnings({"unused","WeakerAccess"})
    public static class Filter {

        private Map<String,Object> options;

        public Filter(){
            options=new HashMap<>();
        }

        public Filter byRepresentation(Representation representation){
            options.put("representation",representation.name);
            return this;
        }

        public Filter byName(String name){
            options.put("q",name);
            return this;
        }

        public Filter nextPage(Page<?> page){
            if(page!=null){
                if(!page.isLast){
                    options.put("offset",page.futureOffset);
                }
            }
            return this;
        }

        public Filter limit(int limit){
            options.put("limit",limit);
            return this;
        }

        public Filter offset(int offset){
            options.put("offset",offset);
            return this;
        }

        public static Filter start(){
            return new Filter();
        }

        public Map<String,Object> createOptions(){
            return options;
        }
    }
}