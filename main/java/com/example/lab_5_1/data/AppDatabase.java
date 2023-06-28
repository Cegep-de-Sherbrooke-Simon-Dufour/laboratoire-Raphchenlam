package com.example.lab_5_1.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Item.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
