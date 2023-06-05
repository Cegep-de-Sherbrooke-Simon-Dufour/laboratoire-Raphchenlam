package com.example.lab_5_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String _newName;
    String _newEmail;
    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users.add(new User ("maxime marchand", "mmm@gmail.com"));
        users.add(new User ("maxime marchand", "mmm@gmail.com"));
        users.add(new User ("maxime marchand", "mmm@gmail.com"));


        RecyclerView recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter adapter = new CustomAdapter();
        recycler.setAdapter(adapter);
        adapter.submitList(new ArrayList<>(users));

        ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent Data =result.getData();
                            _newName = Data.getStringExtra("name");
                            _newEmail = Data.getStringExtra("email");
                            User newUser = new User(_newName, _newEmail);
                            users.add(newUser);
                            adapter.submitList(new ArrayList<>(users));
                        }
                    }
                }
        );

        adapter.setCallbackedUser(new RecyclerCallback<User>() {
            @Override
            public void returnValue(User object) {
                users.remove(object);
                adapter.submitList(new ArrayList<>(users));
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