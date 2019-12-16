package com.example.sep4android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ui.ChangePassword;
import com.example.sep4android.ui.LogInActivity;
import com.example.sep4android.ui.SignUpActivity;
import com.example.sep4android.ui.plant.PlantFragment;
import com.example.sep4android.ui.plantProfile.PlantProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    View palnt, profile;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.tb);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(null);


        TextView textview = findViewById(R.id.text);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_plant, R.id.navigation_plantProfile)
                .build();

        palnt = findViewById(R.id.navigation_plant);
        profile = findViewById(R.id.navigation_plantProfile);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, new PlantFragment());
        transaction.addToBackStack(null).commit();

        onTabSelected();


        onFragmentChange();
    }


    public void onTabSelected(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);


    }
    public void addFragementOnTop(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void onFragmentChange() {
        findViewById(R.id.navigation_plant).setOnClickListener(v -> {
            PlantReponsitory.getInstance().UpdatePalnts(UserRepository.getInstance().getUserEmail());
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new PlantFragment());
            fragmentTransaction.commit();
        });
        findViewById(R.id.navigation_plantProfile).setOnClickListener(v -> {
            PlantProfileReponsitory.getInstance().UpdateProfiles(UserRepository.getInstance().getUserEmail());
            //addFragementOnTop( new PlantFragment());
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new PlantProfileFragment());
            fragmentTransaction.addToBackStack(BACK_STACK_ROOT_TAG).commit();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();

        if (id == R.id.changePassword){
            //change password fragment
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new ChangePassword());
            fragmentTransaction.commit();

        }
        if(id == R.id.logout){builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to log out ?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent myIntent = new Intent(getBaseContext(),   LogInActivity.class);
                    startActivity(myIntent);
                    finish();
                }

            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            dialog = builder.create();
            dialog.show();

        }
        if (id == R.id.deleteAccount){
            builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to delete this account ?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //delete api

                    UserRepository.getInstance().deleteUser(UserRepository.getInstance().getUserEmail());

                    Intent myIntent = new Intent(getBaseContext(),   SignUpActivity.class);
                    startActivity(myIntent);
                }

            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            dialog = builder.create();
            dialog.show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}

