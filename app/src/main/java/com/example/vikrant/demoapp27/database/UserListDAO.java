package com.example.vikrant.demoapp27.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by Vikrant on 03-01-2018.
 */

@Dao
public interface UserListDAO {

    @Query("select * from userList")
    LiveData<List<UserList>> loadAllUser();

    @Query("select * from userList where id = :id")
    UserList loadUserById(int id);

    @Insert(onConflict = IGNORE)
    void insertUser(UserList userList);


    @Query("DELETE FROM userList")
    void deleteAll();
}
