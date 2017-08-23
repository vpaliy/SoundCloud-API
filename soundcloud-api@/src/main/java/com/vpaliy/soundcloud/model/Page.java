package com.vpaliy.soundcloud.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.vpaliy.soundcloud.utils.Adapter;

import java.util.List;

public class Page<T> implements Adapter.PostProcessable{

    public List<T> collection;

    @SerializedName("next_href")
    private String next_href;

    public boolean isLast;
    public int futureOffset;


    @Override
    public void postProcess() {
        isLast=next_href==null|| TextUtils.isEmpty(next_href);
        futureOffset=toNumber("offset=");
    }

    private int toNumber(String pattern){
        if(next_href!=null){
            int index=next_href.indexOf(pattern);
            int number=0;
            while(true){
                int tempIndex=index+pattern.length();
                if(next_href.length()<=tempIndex||!Character.isDigit(next_href.charAt(tempIndex))){
                    break;
                }
                number*=10;
                number+=next_href.charAt(tempIndex)-'0';
                index++;
            }
            return number;
        }
        return -1;
    }

}
