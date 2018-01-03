package com.example.vikrant.demoapp27;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.vikrant.demoapp27.database.UserList;

import java.util.List;

/**
 * Created by Vikrant on 03-01-2018.
 */

public class UserViewModel extends AndroidViewModel {

    private UserListRepository mRepository;

    private LiveData<List<UserList>> mAllBrands;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserListRepository(application);
        mAllBrands = mRepository.getAll();
    }


    LiveData<List<UserList>> getAllUser() {
        return mAllBrands;
    }

    public void insert(UserList userList) {
        mRepository.insert(userList);
    }
}
