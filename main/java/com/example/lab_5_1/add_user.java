package com.example.lab_5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_user extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_user);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(saveBtnListener);

        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }

    View.OnClickListener saveBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            EditText inputName = findViewById(R.id.nameInput);
            String _name = inputName.getText().toString();

            EditText inputEmail = findViewById(R.id.emailInput);
            String _email = inputEmail.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", _name);
            resultIntent.putExtra("email", _email);

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };


    View.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };
}