package com.example.vikrant.demoapp27.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by Vikrant on 03-01-2018.
 */

public interface UserListDAO {

    @Query("select * from userList")
    LiveData<List<UserList>> loadAllBrands();

    @Query("select * from userList where id = :id")
    UserList loadBrandById(int id);

    @Insert(onConflict = IGNORE)
    void insertBrand(UserList userList);


    @Query("DELETE FROM userList")
    void deleteAll();
}
