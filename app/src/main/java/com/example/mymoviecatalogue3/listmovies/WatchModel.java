package com.example.mymoviecatalogue3.listmovies;


import android.os.Parcel;
import android.os.Parcelable;

public class WatchModel implements Parcelable {

    private String title;

    private String overview;

    private Integer poster;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeValue(this.poster);

    }

    private WatchModel(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = (Integer) in.readValue(Integer.class.getClassLoader());


    }

    public static final Parcelable.Creator<WatchModel> CREATOR = new Creator<WatchModel>() {
        @Override
        public WatchModel createFromParcel(Parcel source) {
            return new WatchModel(source);
        }

        @Override
        public WatchModel[] newArray(int size) {
            return new WatchModel[size];
        }
    };
}
