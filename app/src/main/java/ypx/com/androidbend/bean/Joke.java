package ypx.com.androidbend.bean;


import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;

/**
 * Created by S01 on 2017/5/6.
 */

public class Joke implements Parcelable {
    private String id;
    private String title;

    private String content;


    public Joke() {
    }

    public Joke(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
