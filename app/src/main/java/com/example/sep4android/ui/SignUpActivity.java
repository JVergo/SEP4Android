package com.example.sep4android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sep4android.Model.User;
import com.example.sep4android.R;
import com.example.sep4android.RDS.UserRepository;

public class SignUpActivity extends AppCompatActivity {
    Button login;
    Button signup;
    EditText email;
    EditText pass;
    EditText conf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.editText_email);
        pass = findViewById(R.id.editText_password);
        conf = findViewById(R.id.editText_passwordconfirm);

        login = findViewById(R.id.login);
        login.setOnClickListener(view -> {
            Intent myIntent = new Intent(getBaseContext(),  LogInActivity.class);
            startActivity(myIntent);
        });

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(view -> {
            boolean guilty = false;
            //send to api
            User user = new User(email.getText().toString(), pass.getText().toString());
            if ((!pass.getText().toString().equals(conf.getText().toString()))) {
                guilty = true;
                Toast.makeText(getBaseContext(), "password do not match", Toast.LENGTH_LONG).show();
            }
            if(pass.getText().equals("") || conf.getText().equals(""))
            {
                guilty = true;
                Toast.makeText(getBaseContext(), "password not set", Toast.LENGTH_LONG).show();
            }
            if(user.getEmail().equals("")) {
                guilty = true;
                Toast.makeText(getBaseContext(), "Email needed", Toast.LENGTH_LONG).show();
            }

            if(!guilty) {
                UserRepository.getInstance().sendAccountRequest(user, getBaseContext());
            }

        });
    }
}
