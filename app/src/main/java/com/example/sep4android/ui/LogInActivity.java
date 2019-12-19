package com.example.sep4android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sep4android.Model.User;
import com.example.sep4android.R;
import com.example.sep4android.RDS.UserRepository;

public class LogInActivity extends AppCompatActivity {
    Button login;
    Button signup;
    EditText email;
    EditText pass;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.editText_email);
        pass = findViewById(R.id.editText_password);

        login.setOnClickListener(view -> {
            User logUser = new User(email.getText().toString(), pass.getText().toString());
            //verify API
            UserRepository.getInstance().loginReques(logUser, getBaseContext());
        });

        signup.setOnClickListener(view -> {
            Intent myIntent = new Intent(getBaseContext(),  SignUpActivity.class);
            startActivity(myIntent);
        });
    }
}
