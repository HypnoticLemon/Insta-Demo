package com.example.vikrant.demoapp27;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.vikrant.demoapp27.database.AppDatabase;
import com.example.vikrant.demoapp27.database.UserList;
import com.example.vikrant.demoapp27.database.UserListDAO;

import java.util.List;

/**
 * Created by Vikrant on 03-01-2018.
 */

public class UserListRepository {

    private UserListDAO userListDAO;
    private LiveData<List<UserList>> mAllBrands;

    UserListRepository(Application application) {
        AppDatabase db = AppDatabase.getInMemoryDatabase(application);
        userListDAO = db.userListModel();
        mAllBrands = userListDAO.loadAllBrands();
    }

    LiveData<List<UserList>> getAll() {
        return mAllBrands;
    }


    public void insert(UserList userList) {
        new insertAsyncTask(userListDAO).execute(userList);
    }

    private static class insertAsyncTask extends AsyncTask<UserList, Void, Void> {

        private UserListDAO mAsyncTaskDao;

        insertAsyncTask(UserListDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(UserList... brandLists) {
            mAsyncTaskDao.insertBrand(brandLists[0]);
            return null;
        }
    }
}
