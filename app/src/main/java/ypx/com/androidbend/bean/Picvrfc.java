package ypx.com.androidbend.bean;

import android.os.Parcel;
import android.os.Parcelable;



/**
 * Created by YPX on 2017/5/7.
 */

public class Picvrfc implements Parcelable{
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public Picvrfc() {
    }

    public Picvrfc(String ctime, String title, String description, String picUrl, String url) {
        this.ctime = ctime;
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    protected Picvrfc(Parcel in) {
        ctime = in.readString();
        title = in.readString();
        description = in.readString();
        picUrl = in.readString();
        url = in.readString();
    }

    public static final Creator<Picvrfc> CREATOR = new Creator<Picvrfc>() {
        @Override
        public Picvrfc createFromParcel(Parcel in) {
            return new Picvrfc(in);
        }

        @Override
        public Picvrfc[] newArray(int size) {
            return new Picvrfc[size];
        }
    };

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ctime);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(picUrl);
        dest.writeString(url);
    }
}
