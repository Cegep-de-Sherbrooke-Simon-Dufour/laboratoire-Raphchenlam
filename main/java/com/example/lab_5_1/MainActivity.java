package com.example.lab_5_1; // lab 6.1 in reality

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    String _newName;
    String _newEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserListViewModel viewModel = new ViewModelProvider(this).get(UserListViewModel.class);

        viewModel.addUser("Maxime Marchand", "mmm@gmail.com");
        viewModel.addUser("Raphaël Chenard-Lamothe", "raphael.chenard@gmail.com");
        viewModel.addUser("David Beaudry", "d.beaudry@gmail.com");
        viewModel.addUser("Francis Maynard", "F.maynard@gmail.com");

        //
        RecyclerView recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter adapter = new CustomAdapter();
        recycler.setAdapter(adapter);
        //

        viewModel.getUsers().observe(this, users -> {
            adapter.submitList(new ArrayList<>(users));
        });

        ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent Data =result.getData();
                            _newName = Data.getStringExtra("name");
                            _newEmail = Data.getStringExtra("email");
                            viewModel.addUser(_newName, _newEmail);
                        }
                    }
                }
        );

        adapter.setCallbackedUser(new RecyclerCallback<User>() {
            @Override
            public void returnValue(User user) {
                viewModel.removeUser(user);
            }
        });

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, add_user.class);
                mGetContent.launch(intent);
            }
        });

    }


}