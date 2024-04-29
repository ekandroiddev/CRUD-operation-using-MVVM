package com.example.crudoperationmvvm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ItemTable.class}, version = 1, exportSchema = false)
public abstract class RoomDataBase extends RoomDatabase {
    private static final String DB_NAME = "app_Database";
    private static RoomDataBase RoomDataBaseInstance;

    public static synchronized RoomDataBase getInstance(Context context) {
        if (RoomDataBaseInstance == null) {
            RoomDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(), RoomDataBase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return RoomDataBaseInstance;
    }

    public abstract ItemDao itemDao();

}
