package com.vpaliy.soundcloud.auth;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings({"unused","WeakerAccess"})
class Connect implements Parcelable{
    final String url;
    final String redirectUri;

    public Connect(Parcel parcel){
        this.url=parcel.readString();
        this.redirectUri=parcel.readString();
    }

    public Connect(String url, String redirectUri){
        this.url=url;
        this.redirectUri=redirectUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(redirectUri);
    }

    public static Creator<Connect> CREATOR=new Creator<Connect>() {
        @Override
        public Connect createFromParcel(Parcel source) {
            return new Connect(source);
        }

        @Override
        public Connect[] newArray(int size) {
            return new Connect[size];
        }
    };
}
