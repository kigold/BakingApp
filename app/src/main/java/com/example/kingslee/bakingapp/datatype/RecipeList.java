package com.example.kingslee.bakingapp.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KingsLee on 6/24/2017.
 */

public class RecipeList implements Parcelable {
    int id;
    String name, image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if ( image.equals(null) || image.equals("")){
            this.image = "path null";
        }else{
            this.image = image;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
    }

    public RecipeList() {
    }

    protected RecipeList(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<RecipeList> CREATOR = new Parcelable.Creator<RecipeList>() {
        @Override
        public RecipeList createFromParcel(Parcel source) {
            return new RecipeList(source);
        }

        @Override
        public RecipeList[] newArray(int size) {
            return new RecipeList[size];
        }
    };
}
