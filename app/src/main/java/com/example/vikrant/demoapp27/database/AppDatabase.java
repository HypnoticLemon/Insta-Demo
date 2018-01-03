package com.example.vikrant.demoapp27.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Vikrant on 03-01-2018.
 */

@Database(entities = {UserList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private String TAG = AppDatabase.class.getSimpleName();
    private static AppDatabase INSTANCE;

    //public abstract BrandListDao brandListModel();

    public abstract UserListDAO userListModel();


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);

                }
            };


    public static AppDatabase getInMemoryDatabase(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
