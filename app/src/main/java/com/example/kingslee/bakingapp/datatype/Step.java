package com.example.kingslee.bakingapp.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KingsLee on 6/23/2017.
 */

public class Step implements Parcelable {
    int id;
    String shortDesc, desc, videoUrl, thumbnail;

    public Step() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        if ( videoUrl.equals(null) || videoUrl.equals("")){
            this.videoUrl = "path null";
        }else{
            this.videoUrl = videoUrl;
        }
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        if ( thumbnail.equals(null) || thumbnail.equals("")){
            this.thumbnail = "path null";
        }else{
            this.thumbnail = thumbnail;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.shortDesc);
        dest.writeString(this.desc);
        dest.writeString(this.videoUrl);
        dest.writeString(this.thumbnail);
    }

    protected Step(Parcel in) {
        this.id = in.readInt();
        this.shortDesc = in.readString();
        this.desc = in.readString();
        this.videoUrl = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
