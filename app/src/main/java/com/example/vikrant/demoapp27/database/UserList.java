package com.example.vikrant.demoapp27.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Vikrant on 03-01-2018.
 */

@Entity
public class UserList {

    @PrimaryKey
    @NonNull
    public String id;

    public String name;

    public String user_name;

    public String follows;

    public String followed_by;

    public String profile_pic;

    public UserList(@NonNull String id, String name, String user_name, String follows, String followed_by, String profile_pic) {
        this.id = id;
        this.name = name;
        this.user_name = user_name;
        this.follows = follows;
        this.followed_by = followed_by;
        this.profile_pic = profile_pic;
    }


    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFollows() {
        return follows;
    }

    public void setFollows(String follows) {
        this.follows = follows;
    }

    public String getFollowed_by() {
        return followed_by;
    }

    public void setFollowed_by(String followed_by) {
        this.followed_by = followed_by;
    }
}
