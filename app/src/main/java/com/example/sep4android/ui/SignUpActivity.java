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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(),  LogInActivity.class);
                startActivity(myIntent);
            }
        });

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send to api
                User user = new User();
                user.setEmail(email.getText().toString());
                user.setPassword(pass.getText().toString());
                if ((!pass.getText().toString().equals(conf.getText().toString()) )|| pass.getText().equals(null) || conf.getText().equals(null)){
                    Toast.makeText(getBaseContext(), "password not equal or empty", Toast.LENGTH_LONG).show();
                    Log.i("Danielaa","not equal or empty");
                }
                else {
                    UserRepository.getInstance().sendAccountRequest(user, getBaseContext());
                }

            }
        });
    }
}
