package com.vpaliy.soundcloud.model;

import android.net.Uri;
import android.text.TextUtils;

import com.vpaliy.soundcloud.utils.Adapter;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Page<T> implements Adapter.PostProcessable {

    public List<T> collection;

    @SerializedName("next_href")
    private String next_href;

    public boolean isLast;
    public int futureOffset;
    public String query;

    public Page() {
    }

    @Override
    public void postProcess() {
        isLast = next_href == null || TextUtils.isEmpty(next_href);
        futureOffset = toNumber("offset=");
        query();

    }

    private void query() {
        if (next_href != null) {
            int index = next_href.indexOf("&q=") + 3;
            if (next_href.length() > index) {
                int lastIndex = next_href.indexOf("&", index);
                query = next_href.substring(index, lastIndex != -1 ? lastIndex : next_href.length());
                query = Uri.decode(query);
            }
        }
    }

    private int toNumber(String pattern) {
        if (next_href != null) {
            int index = next_href.indexOf(pattern);
            int number = 0;
            while (true) {
                int tempIndex = index + pattern.length();
                if (next_href.length() <= tempIndex || !Character.isDigit(next_href.charAt(tempIndex))) {
                    break;
                }
                number *= 10;
                number += next_href.charAt(tempIndex) - '0';
                index++;
            }
            return number;
        }
        return -1;
    }

}
