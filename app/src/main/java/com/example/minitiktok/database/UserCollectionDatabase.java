package com.example.minitiktok.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserCollectionEntity.class}, version = 1,exportSchema = false)
public abstract class UserCollectionDatabase extends RoomDatabase {
    private static volatile UserCollectionDatabase INSTANCE;

    public abstract UserCollectionDao userCollectionDao();

    public UserCollectionDatabase() {

    }

    public static UserCollectionDatabase inst(Context context) {
        if (INSTANCE == null) {
            synchronized (UserCollectionDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserCollectionDatabase.class, "collect.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
