package com.example.lab_5_1.di;

import android.content.Context;

import androidx.room.Room;

import com.example.lab_5_1.data.AppDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    public static AppDatabase provideDatabase (@ApplicationContext Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }
}
