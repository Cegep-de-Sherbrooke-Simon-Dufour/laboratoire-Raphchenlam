package com.example.lab_5_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserListViewModel extends ViewModel {
    private final UserRepository repository;

    @Inject
    public UserListViewModel(UserRepository repository){
        this.repository = repository;
    }

    public void addUser(String name, String email){repository.addUser(new User(name, email));}

    public void removeUser(User user){repository.removeUser(user);}

    public LiveData<List<User>> getUsers() {return repository.getUsers();};
}
