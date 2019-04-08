package com.example.manabie.resources.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.manabie.resources.model.ManabieItem;

@Database(entities = {ManabieItem.class}, version = 1)
public abstract class ManabieDatabase extends RoomDatabase {
    private static ManabieDatabase sInstance;

    public static synchronized ManabieDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ManabieDatabase.class,
                    "manabie_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return sInstance;
    }

    public abstract ManabieDao manabieDao();

}
