package com.example.lab_5_1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE user_id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE user_name LIKE :name AND " + "user_email LIKE :email LIMIT 1")
    User findByInfo(String name, String email);

    @Insert
    void insertAll(User ... user_s);

    @Delete
    void delete(User user);
}
