package ypx.com.androidbend.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by YPX on 2017/5/2.
 */

public class User extends BmobObject implements Serializable {
    private String username;
    private String password;

    public User() {
    }

    public User(String tableName) {
        super(tableName);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
