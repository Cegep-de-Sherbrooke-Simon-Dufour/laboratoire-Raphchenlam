package com.example.lab_5_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    @Inject
    public UserRepository(){
        users.add(new User("Maxime Marchand", "mmm@gmail.com"));
        users.add(new User("Raphaël Chenard-Lamothe", "raphael.chenard@gmail.com"));
        users.add(new User("David Beaudry", "d.beaudry@gmail.com"));
        users.add(new User("Francis Maynard", "F.maynard@gmail.com"));
        usersLiveData.setValue(new ArrayList<>(users));

    }

    private ArrayList<User> users = new ArrayList<>();
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>(new ArrayList<>(users));

    public void addUser(User user){
        users.add(user);
        usersLiveData.setValue(new ArrayList<>(users));
    }

    public void removeUser(User user){
        users.remove(user);
        usersLiveData.setValue(new ArrayList<>(users));
    }
    public LiveData<List<User>> getUsers(){ return usersLiveData;};
}
