package com.example.lab_5_1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Insert
    void insertAll(User ... user_s);

    @Delete
    void delete(User user);


    @Query("SELECT * FROM Items WHERE ownerId = :userId")
    LiveData<List<Item>> getAllItems(int userId);

    @Insert
    void insertAll(Item ... item_s);

    @Delete
    void delete(Item item);
}
