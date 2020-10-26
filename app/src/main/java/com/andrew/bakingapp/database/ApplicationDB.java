package com.andrew.bakingapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.andrew.bakingapp.database.dao.BakeDao;
import com.andrew.bakingapp.database.entity.BakeEntity;
import com.andrew.bakingapp.utils.Constant;

@Database(entities = {BakeEntity.class}, version = 1)
public abstract class ApplicationDB extends RoomDatabase {
    private static ApplicationDB instance = null;

    public static ApplicationDB getInstance(Context mContext) {
        if (instance == null) {
            instance = Room.databaseBuilder(mContext, ApplicationDB.class, Constant.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BakeDao bakeDao();
}
