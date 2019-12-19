package com.example.sep4android.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.User;
import com.example.sep4android.R;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ViewModel.ChangePasswordViewModel;

public class ChangePassword extends Fragment {

    private ChangePasswordViewModel mViewModel;

    private Button saveBtn;
    private EditText oldPass, newPass;
    private User user;

    private View root;

    public static ChangePassword newInstance() {
        return new ChangePassword();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_change_password, container, false);
        saveBtn = root.findViewById(R.id.buttonSavePass);
        newPass = root.findViewById(R.id.editText_password);
        oldPass = root.findViewById(R.id.neweditText_password);


        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);
        if (UserRepository.getInstance().getUser() == null) {
            UserRepository.getInstance().getUserFromApi(UserRepository.getInstance().getUserEmail());
        }
        mViewModel.getUser().observe(getActivity(), user1 -> {
            user = user1;
        });
        saveBtn.setOnClickListener(v -> save());
    }


    public void save() {
        /*if (!(oldPass.getText().toString().equals( user.getPassword()))) {

        Toast.makeText(getContext(), "Your password is wrong", Toast.LENGTH_LONG).show();
        Log.i("Daniela","password wrong");
        }
        else {
         */
        if ((!newPass.getText().toString().equals(oldPass.getText().toString()))) {
            Toast.makeText(getContext(), "password do not match", Toast.LENGTH_LONG).show();
            return;
        }
        if (!(newPass.getText().toString().equals(""))) {
            user.setPassword(newPass.getText().toString());
            user.setEmail(UserRepository.getInstance().getUserEmail());
            UserRepository.getInstance().updateUser(user);

            Toast.makeText(getContext(), "Your password is changed now!", Toast.LENGTH_LONG).show();
            Log.i("daniela", "password change");
        } else {
            Toast.makeText(getContext(), "Your password can not be empty", Toast.LENGTH_LONG).show();
        }
        //}

    }

}