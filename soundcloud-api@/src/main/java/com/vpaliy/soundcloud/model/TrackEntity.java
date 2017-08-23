package com.vpaliy.soundcloud.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TrackEntity {

    public String id;
    public String created_at;
    public String user_id;
    public MiniUserEntity user;
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

    public enum Visibility {
        ALL("all"),
        PUBLIC("public"),
        PRIVATE("private");

        private final String filter;

        Visibility(String filter) {
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

    @SuppressWarnings({"WeakerAccess"})
    public static class Filter {
        private Map<String,Object> options;

        public Filter(){
            options=new HashMap<>();
        }

        public Filter byName(String name){
            options.put("q",name);
            return this;
        }

        public Filter limit(int limit){
            options.put("limit",limit);
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

        public Filter offset(int offset){
            options.put("offset",offset);
            return this;
        }

        public Filter byGenres(String... genres){
            if(genres!=null) {
                options.put("genres", convert(genres));
            }
            return this;
        }

        public Filter byTags(String...tags){
            if(tags!=null) {
                options.put("tags", convert(tags));
            }
            return this;
        }

        public Filter byLicense(License license){
            if(license!=null){
                options.put("license",license.license);
            }
            return this;
        }

        public Filter byTypes(Type...types){
            if(types!=null){
                options.put("types",convert(types));
            }
            return this;
        }

        public Filter byBPM(int from, int to){
            options.put("bpm[from]",from);
            options.put("bpm[to]",to);
            return this;
        }

        public Filter withPagination(){
            options.put("linked_partitioning",-1);
            return this;
        }

        public Filter byVisibility(Visibility visibility){
            if(visibility!=null){
                options.put("filter",visibility.filter);
            }
            return this;
        }

        @SuppressWarnings("all")
        private <T> String convert(T...strings){
            return Arrays.toString(strings)
                    .replaceAll("[\\[.\\].\\s+]", "");
        }

        public Filter byTime(Date from, Date to){
            options.put("created_at[from]",from);
            options.put("created_at[to]",to);
            return this;
        }

        public Filter byDuration(int fromMillis, int toMillis){
            options.put("duration[from]",fromMillis);
            options.put("duration[to]",toMillis);
            return this;
        }

        public Filter byIds(String...ids){
            options.put("ids",convert(ids));
            return this;
        }

        public Map<String,Object> createOptions(){
            return options;
        }

        public static Filter start(){
            return new Filter();
        }
    }
}