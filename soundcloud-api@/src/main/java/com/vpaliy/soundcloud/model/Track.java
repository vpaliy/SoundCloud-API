package com.vpaliy.soundcloud.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Track {

    public String id;
    public String created_at;
    public String user_id;
    public MiniUser user;
    public String title;
    public String permalink;
    public String permalink_url;
    public String uri;
    public String sharing;
    public String embeddable_by;
    public String purchase_url;
    public String artwork_url;
    public String description;
    public String duration;
    public String genre;
    public String tags_list;
    public String label_id;
    public String label_name;
    public String release;
    public String release_day;
    public String release_month;
    public String release_year;
    @SerializedName("streamable")
    public boolean is_streamable;
    @SerializedName("downloadable")
    public boolean is_downloadable;
    public String state;
    public String license;
    public String track_type;
    public String waveform_url;
    public String download_url;
    public String stream_url;
    public String video_url;
    public String bpm;
    public boolean commentable;
    public String isrc;
    public String key_signature;
    public String comment_count;
    public String download_count;
    public String playback_count;
    public String favoritings_count;
    public String original_format;
    public String original_content_size;
    public String asset_data;
    public String artwork_data;
    public boolean user_favorite;

    public enum License {

        ALL_RIGHTS_RESERVED("all-rights-reserved"),
        NO_RIGHTS_RESERVED("no-rights-reserved"),
        CC_ATTRIBUTION("cc-by"),
        CC_ATTRIBUTION_NONCOMMERCIAL("cc-by-nc"),
        CC_ATTRIBUTION_NO_DERIVATIVES("cc-by-nd"),
        CC_ATTRIBUTION_SHARE_ALIKE("cc-by-sa"),
        CC_ATTRIBUTION_NONCOMMERCIAL_NO_DERIVATES("cc-by-nc-nd"),
        CC_ATTRIBUTION_NONCOMMERCIAL_SHARE_ALIKE("cc-by-nc-sa");

        private final String license;

        License(String license) {
            this.license = license;
        }

        @Override
        public String toString() {
            return license;
        }
    }

    public enum Type {

        ORIGINAL("original"),
        REMIX("remix"),
        LIVE("live"),
        RECORDING("recording"),
        SPOKEN("spoken"),
        PODCAST("podcast"),
        DEMO("demo"),
        IN_PROGRESS("in progress"),
        STEM("stem"),
        LOOP("loop"),
        SOUND_EFFECT("sound effect"),
        SAMPLE("sample"),
        OTHER("other");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public enum State {

        PROCESSING("processing"),
        FAILED("failed"),
        FINISHED("finished");

        private final String state;

        State(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return state;
        }
    }

    public enum Filter {
        ALL("all"),
        PUBLIC("public"),
        PRIVATE("private");

        private final String filter;

        Filter(String filter) {
            this.filter = filter;
        }

        @Override
        public String toString() {
            return filter;
        }
    }


    public enum EmbeddableBy {
        ALL("all"),
        ME("me"),
        NONE("none");

        private final String embeddableBy;

        EmbeddableBy(String embeddableBy) {
            this.embeddableBy = embeddableBy;
        }

        @Override
        public String toString() {
            return embeddableBy;
        }
    }
}