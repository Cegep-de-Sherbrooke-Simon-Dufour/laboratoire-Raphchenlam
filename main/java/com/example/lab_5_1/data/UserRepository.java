package com.example.lab_5_1.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final UserDao userDao;
    @Inject
    public UserRepository(AppDatabase database){
        userDao = database.getUserDao();
    }

    public void addUser(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insertAll(user);
        });
    }

    public void removeUser(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(user);
        });
    }
    public LiveData<List<User>> getUsers(){ return userDao.getAll();};



    public void addItem(Item item){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insertAll(item);
        });
    }

    public void removeItem(Item item){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(item);
        });
    }
    public LiveData<List<Item>>  getItems(int userId){ return userDao.getAllItems(userId);};
}
